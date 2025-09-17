package productService.service.event;

import ecommerce.productcommon.dtos.ProductRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductEventServiceImpl implements ProductEventService {
    private final KafkaTemplate<String, ProductRequest> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    @Override
    public void publish(List<ProductRequest> productRequests) {
        productRequests.forEach(product -> kafkaTemplate.send(topic, product.sku(), product).
                whenComplete((result,exception)->{
                    if(exception == null){
                        log.info("✅ Published product sku={} partition={} offset={}",
                                product.sku(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                    else {
                        log.error("❌ Failed to publish product sku={} error={}", product.sku(), exception.getMessage(), exception);
                        // TODO: Optionally add retry mechanism / push to Dead Letter Queue (DLQ)
                    }
                }));
    }
}
