package productService.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import productService.dtos.ProductRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductEventServiceImpl implements ProductEventService {
    private final KafkaTemplate<String, ProductRequest> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    @Override
    public void publish(ProductRequest productRequest) {
        kafkaTemplate.send(topic, productRequest.sku(), productRequest).
                whenComplete((result,exception)->{
                            if(exception == null){
                                log.info("✅ Published product sku={} partition={} offset={}",
                                        productRequest.sku(),
                                        result.getRecordMetadata().partition(),
                                        result.getRecordMetadata().offset());
                            }
                            else {
                                log.error("❌ Failed to publish product sku={} error={}", productRequest.sku(), exception.getMessage(), exception);
                                // TODO: Optionally add retry mechanism / push to Dead Letter Queue (DLQ)
                            }
                        });
    }
}
