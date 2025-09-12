package productService.service.event;

import productService.dtos.ProductRequest;

public interface ProductEventService {
    void publish(ProductRequest productRequest);
}
