package productService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import productService.dtos.ProductRequest;
import productService.dtos.ProductResponse;
import productService.mapper.ProductMapper;
import productService.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Product API", description = "CRUD operations on products")
public class ProductController {

    private final ProductService productService;

    private final ProductMapper mapper;

    @Operation(summary = "Create Product (admin)")
    @PostMapping("/create")
    //need to add security as admin can create product only
    public ResponseEntity<ProductResponse> createProduct(
//          @RequestHeader (value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody ProductRequest productRequest){
        ProductResponse response = productService.saveProduct(mapper.toEntity(productRequest));
     return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get All Products")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        ProductResponse productById = productService.getProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateById(@PathVariable Long id ,
                                                      @RequestBody ProductRequest productRequest){
        return null;
    }


}
