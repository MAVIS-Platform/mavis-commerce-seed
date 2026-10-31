package io.mavis.seed.catalog.messaging;

import io.mavis.seed.catalog.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StockEventListener {

    private final ProductRepository productRepository;

    public StockEventListener(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = "order-events")
    public void onOrderEvent(String message) {
        if (message != null && !message.isBlank()) {
            productRepository.count();
        }
    }
}
