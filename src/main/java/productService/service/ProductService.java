package productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import productService.dtos.ProductResponse;
import productService.mapper.ProductMapper;
import productService.model.Product;
import productService.model.ProductAttribute;
import productService.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository productRepository;
   protected final ProductMapper mapper;

   public List<ProductResponse> getAllProducts(){
       return productRepository.findAll()
               .stream()
               .map(mapper::toResponse)
               .toList();
   }


   public ProductResponse getProductById(Long id){
       Product productById = productRepository.getProductById(id);
       return mapper.toResponse(productById);
   }

   public ProductResponse saveProduct(Product product){

       product.getProductAttributes().forEach(attributes-> attributes.setProduct(product));

       product.getImageUrls().forEach(image -> image.setProduct(product));

       Product save = productRepository.save(product);

       return mapper.toResponse(save);
   }

   public void deleteProductById(Long id){
       productRepository.deleteById(id);
   }

  /* public Product updateProductById(Long id, Product product){
       Product existingProduct = productRepository.getProductById(id);
       return null;
   }*/
}
