package productService.service.query;

import org.springframework.data.domain.Pageable;
import productService.dtos.PagedResponse;
import productService.dtos.ProductResponse;
import productService.exception.ProductNotFoundException;

public interface ProductQueryService {
    PagedResponse<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse getProductById(Long id) throws ProductNotFoundException;

}
