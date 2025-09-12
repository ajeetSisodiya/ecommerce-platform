package productService.service;

import org.springframework.data.domain.Pageable;
import productService.dtos.PagedResponse;
import productService.dtos.ProductResponse;
import productService.exception.ProductNotFoundException;
import productService.model.Product;

public interface ProductService {

    PagedResponse<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse getProductById(Long id) throws ProductNotFoundException;

    ProductResponse saveProduct(Product product);

    void deleteProductById(Long id);

    ProductResponse updateProductById(Long id, Product product) throws ProductNotFoundException;

}
