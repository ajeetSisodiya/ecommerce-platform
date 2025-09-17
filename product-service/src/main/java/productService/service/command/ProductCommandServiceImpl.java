package productService.service.command;

import ecommerce.productcommon.dtos.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import productService.exception.ProductNotFoundException;
import productService.mapper.ProductMapper;
import productService.model.Product;
import productService.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService{

    private final ProductRepository productRepository;
    protected final ProductMapper mapper;
    
    @Override
    public ProductResponse saveProduct(Product product){

        product.getProductAttributes().forEach(attributes-> attributes.setProduct(product));

        product.getImageUrls().forEach(image -> image.setProduct(product));

        Product save = productRepository.save(product);

        return mapper.toResponse(save);
    }

    @Override
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProductById(Long id, Product product) throws ProductNotFoundException {
        Product existingProduct = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product not found with id "+ id));

        Optional.ofNullable(product.getBrand()).ifPresent(existingProduct::setBrand);
        Optional.ofNullable(product.getCategory()).ifPresent(existingProduct::setCategory);
        Optional.ofNullable(product.getDescription()).ifPresent(existingProduct::setDescription);
        Optional.ofNullable(product.getPrice()).ifPresent(existingProduct::setPrice);
        Optional.ofNullable(product.getCurrency()).ifPresent(existingProduct::setCurrency);
        Optional.ofNullable(product.getStatus()).ifPresent(existingProduct::setStatus);

        if(product.getProductAttributes()!=null){
            existingProduct.getProductAttributes().clear();
            product.getProductAttributes().forEach(attr -> {
                attr.setProduct(existingProduct);
                existingProduct.getProductAttributes().add(attr);
            });
        }
        if (product.getImageUrls() != null) {
            existingProduct.getImageUrls().clear();
            product.getImageUrls().
                    forEach(productImage -> {
                        productImage.setProduct(existingProduct);
                        existingProduct.getImageUrls().add(productImage);
                    });
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return mapper.toResponse(updatedProduct);
    }
}
