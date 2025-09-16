package productService.service.event;

import productService.dtos.ProductRequest;

import java.util.List;

public interface ProductEventService {
    void publish(List<ProductRequest> productRequests);
}
