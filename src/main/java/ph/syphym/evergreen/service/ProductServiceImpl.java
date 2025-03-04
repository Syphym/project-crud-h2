package ph.syphym.evergreen.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ph.syphym.evergreen.constant.OrderCriteria;
import ph.syphym.evergreen.constant.OrderDirection;
import ph.syphym.evergreen.dto.ProductDTO;
import ph.syphym.evergreen.entity.Product;
import ph.syphym.evergreen.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class ProductServiceImpl implements ProductService {
    private static final int PAGE_SIZE = 10;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(UUID.fromString(id)).get();

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
    public List<ProductDTO> getProductByCriteria(String criteria, Integer page) {
        List<Product> listOfProducts = productRepository.findByCriteria(criteria, createPageRequest(page));
        return listOfProducts.stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<ProductDTO> getOrderedProducts(OrderCriteria orderCriteria, OrderDirection orderDirection, Integer page) {
        List<Product> products = fetchProducts(orderCriteria, orderDirection, page);
        return products.stream()
                .map(this::convertToDTO)
                .toList();
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

    private Pageable createPageRequest(Integer page) {
        return PageRequest.of(page, PAGE_SIZE);
    }
}
