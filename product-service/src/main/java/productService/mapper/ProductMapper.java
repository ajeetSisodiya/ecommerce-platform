package productService.mapper;


import ecommerce.productcommon.dtos.ProductRequest;
import ecommerce.productcommon.dtos.ProductResponse;
import org.mapstruct.Mapper;
import productService.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse toResponse(Product product);
    Product toEntity(ProductRequest request);
}
