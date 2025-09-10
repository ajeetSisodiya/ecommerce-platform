package productService.mapper;


import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import productService.dtos.PagedResponse;
import productService.dtos.ProductRequest;
import productService.dtos.ProductResponse;
import productService.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    Product toEntity(ProductRequest request);
    PagedResponse<ProductResponse> toPageResponse(Page<Product> productPage);
}
