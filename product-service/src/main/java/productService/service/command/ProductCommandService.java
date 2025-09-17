package productService.service.command;


import ecommerce.productcommon.dtos.ProductResponse;
import productService.exception.ProductNotFoundException;
import productService.model.Product;

public interface ProductCommandService {
    ProductResponse saveProduct(Product product);
    void deleteProductById(Long id);
    ProductResponse updateProductById(Long id, Product product) throws ProductNotFoundException;
}
