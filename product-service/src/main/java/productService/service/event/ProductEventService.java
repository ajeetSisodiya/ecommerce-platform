package productService.service.event;

import ecommerce.productcommon.dtos.ProductRequest;

import java.util.List;

public interface ProductEventService {
    void publish(List<ProductRequest> productRequests);
}
