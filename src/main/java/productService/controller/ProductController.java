package productService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import productService.dto.ProductDto;
import productService.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ProductDto crate(@RequestBody ProductDto productDto){
        return productService.create(productDto);
    }

    @GetMapping("/product/{id}")
    public ProductDto getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
         productService.delete(id);
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts(){
         return productService.getAllProducts();
    }

}
