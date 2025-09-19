package ecommerce.productconsumer.mapper;



import ecommerce.productcommon.event.ProductCreatedEvent;
import ecommerce.productconsumer.model.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductEventMapper {
    Product toEntity(ProductCreatedEvent event);
}
