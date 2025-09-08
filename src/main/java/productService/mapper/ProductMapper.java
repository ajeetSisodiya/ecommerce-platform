package productService.mapper;


import org.mapstruct.Mapper;
import productService.dtos.ProductRequest;
import productService.dtos.ProductResponse;
import productService.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    Product toEntity(ProductRequest request);
}
