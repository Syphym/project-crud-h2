package ph.syphym.evergreen.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ph.syphym.evergreen.constant.OrderCriteria;
import ph.syphym.evergreen.constant.OrderDirection;
import ph.syphym.evergreen.dto.BaseResponseDTO;
import ph.syphym.evergreen.dto.ProductDTO;

import java.util.List;

public interface ProductController {

    @GetMapping("/{id}")
    ResponseEntity<BaseResponseDTO<ProductDTO>> getProduct( @PathVariable("id") String id);

    @GetMapping
    ResponseEntity<BaseResponseDTO<List<ProductDTO>>> getProductsByCriteria(@RequestParam(required = false) String criteria
                                                                            ,@RequestHeader(required = false) Integer page);

    @GetMapping("/order")
    ResponseEntity<BaseResponseDTO<List<ProductDTO>>> getOrderedProducts(@RequestParam OrderCriteria orderCriteria,
                                                                         @RequestParam OrderDirection orderDirection,
                                                                         @RequestHeader(required = false) Integer page);

    @PostMapping
    ResponseEntity<BaseResponseDTO<ProductDTO>> createNewProduct(@Valid @RequestBody ProductDTO productDTO);

    @PutMapping("/{uuid}")
    ResponseEntity<BaseResponseDTO<ProductDTO>> updateExistingProduct( @PathVariable("uuid") String uuid,
                                                                       @Valid @RequestBody ProductDTO productDTO);

    @DeleteMapping("/{uuid}")
    ResponseEntity<BaseResponseDTO<Void>> deleteProduct(@PathVariable("uuid") String uuid);
}
