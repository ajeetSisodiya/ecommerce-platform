package productService.controller;

import ecommerce.productcommon.dtos.PagedResponse;
import ecommerce.productcommon.dtos.ProductRequest;
import ecommerce.productcommon.dtos.ProductResponse;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import productService.exception.ProductNotFoundException;
import productService.mapper.ProductMapper;
import productService.service.command.ProductCommandService;
import productService.service.event.ProductEventService;
import productService.service.query.ProductQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Product API", description = "CRUD operations on products")
public class ProductController {

    private final ProductCommandService productCommandService;

    private final ProductQueryService productQueryService;

    private final ProductEventService productEventService;

    private final ProductMapper mapper;

    @Operation(summary = "Create Product (admin)")
    @PostMapping("/create")
    //need to add security as admin can create product only
    public ResponseEntity<ProductResponse> createProduct(
//          @RequestHeader (value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody ProductRequest productRequest){
        ProductResponse response = productCommandService.saveProduct(mapper.toEntity(productRequest));
     return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Create Products in bulk (admin)")
    @PostMapping("/bulkCreate")
    //need to add security as admin can create product only
    public ResponseEntity<ProductResponse> createBulkProducts(
//          @RequestHeader (value = "Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody List<ProductRequest> productRequests){
        productEventService.publish(productRequests);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Get All Products")
    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponse>> getAllProducts(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        PagedResponse<ProductResponse> allProducts = productQueryService.getAllProducts(pageable);
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }
    @Operation(summary = "Get A Product with Id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) throws ProductNotFoundException {
        ProductResponse productById = productQueryService.getProductById(id);
        return new ResponseEntity<>(productById, HttpStatus.OK);
    }
    @Operation(summary = "Delete a Product")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        productCommandService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update a Product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateById(@PathVariable Long id ,
                                                      @RequestBody ProductRequest productRequest) throws ProductNotFoundException {
        ProductResponse productResponse = productCommandService.updateProductById(id, mapper.toEntity(productRequest));
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }
}
