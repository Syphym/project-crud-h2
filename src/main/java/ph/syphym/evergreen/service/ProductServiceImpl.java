package ph.syphym.evergreen.service;

import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ph.syphym.evergreen.constant.OrderCriteria;
import ph.syphym.evergreen.constant.OrderDirection;
import ph.syphym.evergreen.dto.ProductDTO;
import ph.syphym.evergreen.entity.Category;
import ph.syphym.evergreen.entity.Product;
import ph.syphym.evergreen.exception.ProductNotFoundException;
import ph.syphym.evergreen.repository.CategoryRepository;
import ph.syphym.evergreen.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

@Service
public class ProductServiceImpl implements ProductService {
    private static final int PAGE_SIZE = 10;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Cacheable("GET_PRODUCT_BY_ID")
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(UUID.fromString(id))
                .orElseThrow(ProductNotFoundException::new);

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .manufacturer(product.getManufacturer())
                .category(product.getCategory() != null ? product.getCategory().getName() : null)
                .region(product.getRegion())
                .build();
    }

    @Override
    @Cacheable("GET_PRODUCT_BY_CRITERIA")
    public List<ProductDTO> getProductByCriteria(String criteria, Integer page) {
        List<Product> listOfProducts = productRepository.findByCriteria(criteria, createPageRequest(page));
        return listOfProducts.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Cacheable("GET_PRODUCT_BY_ORDER")
    public List<ProductDTO> getOrderedProducts(OrderCriteria orderCriteria, OrderDirection orderDirection, Integer page) {
        List<Product> products = fetchProducts(orderCriteria, orderDirection, page);
        return products.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {

        Category getCategory = categoryRepository.findByName(productDTO.getCategory());

        Product productToBeSaved = buildProductEntity(productDTO, getCategory);

        Product savedProduct = productRepository.save(productToBeSaved);

        return convertToProductDTO(savedProduct);
    }

    @Override
    @CachePut(value = {"GET_PRODUCT_BY_ORDER", "GET_PRODUCT_BY_ID", "GET_PRODUCT_BY_CRITERIA"}, key = "#id")
    @CacheEvict(value = {"GET_PRODUCT_BY_ORDER", "GET_PRODUCT_BY_ID", "GET_PRODUCT_BY_CRITERIA"}, key = "#id", allEntries = true)
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        UUID productId = UUID.fromString(id);

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        Category category = categoryRepository.findByName(productDTO.getCategory());
        Product productToBeSaved = updateProductEntity(productDTO, category, product);
        Product savedProduct = productRepository.save(productToBeSaved);
        return convertToProductDTO(savedProduct);
    }

    private List<Product> fetchProducts(OrderCriteria orderCriteria, OrderDirection orderDirection, Integer page) {
        Map<OrderCriteria, Function<OrderDirection, List<Product>>> criteriaMap = new HashMap<>();

        criteriaMap.put(OrderCriteria.NAME, dir -> dir == OrderDirection.ASC
                ? productRepository.findAllByOrderByNameAsc(createPageRequest(page))
                : productRepository.findAllByOrderByNameDesc(createPageRequest(page)));

        criteriaMap.put(OrderCriteria.PRICE, dir -> dir == OrderDirection.ASC
                ? productRepository.findAllByOrderByPriceAsc(createPageRequest(page))
                : productRepository.findAllByOrderByPriceDesc(createPageRequest(page)));

        return criteriaMap.getOrDefault(orderCriteria, dir -> productRepository.findAll()).apply(orderDirection);
    }

    private ProductDTO convertToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .manufacturer(product.getManufacturer())
                .category(product.getCategory() != null ? product.getCategory().getName() : null)
                .region(product.getRegion())
                .build();
    }

    private Product buildProductEntity(ProductDTO productDTO, Category category) {
        return Product.builder()
                .id(UUID.randomUUID())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .manufacturer(productDTO.getManufacturer())
                .region(productDTO.getRegion())
                .category(category)
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();
    }

    private Product updateProductEntity(ProductDTO productDTO, Category category, Product existingProduct) {
        return Product.builder()
                .id(existingProduct.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .manufacturer(productDTO.getManufacturer())
                .region(productDTO.getRegion())
                .category(category)
                .created(existingProduct.getCreated())
                .updated(LocalDateTime.now())
                .build();
    }

    private ProductDTO convertToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .manufacturer(product.getManufacturer())
                .region(product.getRegion())
                .category(product.getCategory().getName())
                .build();
    }

    private Pageable createPageRequest(Integer page) {
        return PageRequest.of(page, PAGE_SIZE);
    }
}
