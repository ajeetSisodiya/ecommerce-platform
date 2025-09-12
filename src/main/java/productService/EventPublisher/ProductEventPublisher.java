package productService.EventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import productService.dtos.ProductRequest;

@Component
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final KafkaTemplate<String, ProductRequest> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public void publish(ProductRequest productRequest) {
        kafkaTemplate.send(topic, productRequest.sku(), productRequest);
    }
}