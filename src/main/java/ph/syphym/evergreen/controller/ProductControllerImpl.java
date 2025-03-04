package ph.syphym.evergreen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.syphym.evergreen.constant.OrderCriteria;
import ph.syphym.evergreen.constant.OrderDirection;
import ph.syphym.evergreen.dto.BaseResponseDTO;
import ph.syphym.evergreen.dto.ProductDTO;
import ph.syphym.evergreen.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v2/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<BaseResponseDTO<ProductDTO>> getProduct(String id) {
        return ResponseEntity.ok(new BaseResponseDTO<>("200","Success", productService.getProductById(id)));
    }

    @Override
    public ResponseEntity<BaseResponseDTO<List<ProductDTO>>> getProductsByCriteria(String criteria,Integer page) {
        return ResponseEntity.ok(new BaseResponseDTO<>("200", "Success", productService.getProductByCriteria(criteria, page)));
    }

    @Override
    public ResponseEntity<BaseResponseDTO<List<ProductDTO>>> getOrderedProducts(OrderCriteria orderCriteria, OrderDirection orderDirection, Integer page) {
        return ResponseEntity.ok(new BaseResponseDTO<>("200", "Success", productService.getOrderedProducts(orderCriteria, orderDirection, page)));
    }
}
