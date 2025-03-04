package ph.syphym.evergreen.service;

import ph.syphym.evergreen.constant.OrderCriteria;
import ph.syphym.evergreen.constant.OrderDirection;
import ph.syphym.evergreen.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(String id);

    List<ProductDTO> getProductByCriteria(String criteria, Integer page);

    List<ProductDTO> getOrderedProducts(OrderCriteria orderCriteria, OrderDirection orderDirection, Integer page);
}
