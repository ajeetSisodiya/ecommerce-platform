package productService.service.query;

import ecommerce.productcommon.dtos.PagedResponse;
import ecommerce.productcommon.dtos.ProductResponse;
import org.springframework.data.domain.Pageable;

import productService.exception.ProductNotFoundException;

public interface ProductQueryService {
    PagedResponse<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse getProductById(Long id) throws ProductNotFoundException;

}
