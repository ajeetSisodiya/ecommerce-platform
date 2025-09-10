package productService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import productService.dtos.PagedResponse;
import productService.dtos.ProductResponse;
import productService.mapper.ProductMapper;
import productService.model.Product;
import productService.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository productRepository;
   protected final ProductMapper mapper;

   public PagedResponse<ProductResponse> getAllProducts(Pageable pageable){
       Page<Product> all = productRepository.findAll(pageable);
       return mapper.toPageResponse(all);
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
