package productService.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productService.dto.ProductDto;
import productService.model.Product;
import productService.model.ProductAttribute;
import productService.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductDto create(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        // Link attributes to the parent product
        if (product.getProductAttributes() != null) {
            for (ProductAttribute attr : product.getProductAttributes()) {
                attr.setProduct(product);
            }
        }
        productRepository.save(product);
        return productDto;
    }

    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return modelMapper.map(product, ProductDto.class);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ProductDto.class))
                .toList();
    }
}
