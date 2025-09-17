package ecommerce.productconsumer.event;

import ecommerce.productcommon.dtos.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EventConsumer {

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "product-consumer-group")
    public void Consume(ProductRequest productRequest){
        log.info("Consumed ProductEvent :{}", productRequest);
        //TODO: Save into DB

    }
}
