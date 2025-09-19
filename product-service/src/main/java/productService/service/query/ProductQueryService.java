package productService.service.query;

import ecommerce.productcommon.dtos.PagedResponse;
import ecommerce.productcommon.dtos.ProductResponse;
import ecommerce.productcommon.exception.ProductNotFoundException;
import org.springframework.data.domain.Pageable;

public interface ProductQueryService {
    PagedResponse<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse getProductById(Long id) throws ProductNotFoundException;

}
