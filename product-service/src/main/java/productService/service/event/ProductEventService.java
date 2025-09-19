package productService.service.event;

import ecommerce.productcommon.dtos.ProductRequest;
import ecommerce.productcommon.event.ProductCreatedEvent;

import java.util.List;

public interface ProductEventService {
    void publish(List<ProductCreatedEvent> events);
}
