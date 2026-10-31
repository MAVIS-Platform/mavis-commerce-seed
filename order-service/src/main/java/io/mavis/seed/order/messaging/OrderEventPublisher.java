package io.mavis.seed.order.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderLifecycleEvent(String orderNumber, String status) {
        String payload = "{\"orderNumber\":\"" + orderNumber + "\",\"status\":\"" + status + "\"}";
        kafkaTemplate.send("order-events", payload);
    }
}
