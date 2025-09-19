package ecommerce.productconsumer.event;

import ecommerce.productcommon.event.ProductCreatedEvent;
import ecommerce.productconsumer.model.Product;
import ecommerce.productconsumer.mapper.ProductEventMapper;
import ecommerce.productconsumer.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EventConsumer {
    private final ProductRepository repository;
    private final ProductEventMapper mapper;

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "product-consumer-group", containerFactory = "kafkaListenerContainerFactory")
    public void Consume(List<ProductCreatedEvent> events){
        log.info("Batch received of size:{}", events.size());

        List<Product> products = events.stream().
                                            map(mapper::toEntity).
                                            toList();

        products.
                forEach(product -> product.getProductAttributes().
                        forEach(attr -> attr.setProduct(product)));

        products.
                forEach(product -> product.getImageUrls().
                        forEach(image -> image.setProduct(product)));

        repository.saveAll(products);

        log.info("Saved {} products to DB", products.size());
    }
}
