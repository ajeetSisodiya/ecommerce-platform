package productService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import productService.dtos.PagedResponse;
import productService.dtos.ProductRequest;
import productService.dtos.ProductResponse;
import productService.exception.ProductNotFoundException;
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

    private final KafkaTemplate<String, ProductRequest> kafkaTemplate;

    @Operation(summary = "Create Product (admin)")
    @PostMapping("/create")
    //need to add security as admin can create product only
    public ResponseEntity<ProductResponse> createProduct(
//          @RequestHeader (value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody ProductRequest productRequest){
        ProductResponse response = productService.saveProduct(mapper.toEntity(productRequest));
     return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Products")
    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponse>> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        PagedResponse<ProductResponse> allProducts = productService.getAllProducts(pageable);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
    @Operation(summary = "Get A Product with Id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        ProductResponse productById = productService.getProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
    @Operation(summary = "Delete a Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a Product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateById(@PathVariable Long id ,
                                                      @RequestBody ProductRequest productRequest) throws ProductNotFoundException {
        ProductResponse productResponse = productService.updateProductById(id, mapper.toEntity(productRequest));
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }


}
