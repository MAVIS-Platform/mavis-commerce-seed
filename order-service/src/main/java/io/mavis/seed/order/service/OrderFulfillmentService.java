package io.mavis.seed.order.service;

import io.mavis.seed.order.client.CatalogInventoryClient;
import io.mavis.seed.order.client.PaymentGatewayClient;
import io.mavis.seed.order.domain.OrderEntity;
import io.mavis.seed.order.messaging.OrderEventPublisher;
import io.mavis.seed.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderFulfillmentService {

    private final OrderRepository orderRepository;
    private final CatalogInventoryClient catalogInventoryClient;
    private final PaymentGatewayClient paymentGatewayClient;
    private final OrderEventPublisher orderEventPublisher;

    public OrderFulfillmentService(
            OrderRepository orderRepository,
            CatalogInventoryClient catalogInventoryClient,
            PaymentGatewayClient paymentGatewayClient,
            OrderEventPublisher orderEventPublisher) {
        this.orderRepository = orderRepository;
        this.catalogInventoryClient = catalogInventoryClient;
        this.paymentGatewayClient = paymentGatewayClient;
        this.orderEventPublisher = orderEventPublisher;
    }

    public List<OrderEntity> listAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity findOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    public OrderEntity createAndChargeOrder(OrderEntity draft) {
        if (draft.getQuantity() <= 0) {
            draft.cancelUnpaidOrder();
            throw new IllegalArgumentException("Invalid order quantity");
        }
        catalogInventoryClient.fetchProduct(draft.getProductId());
        draft.beginPaymentReservation();
        OrderEntity saved = orderRepository.save(draft);

        Map<String, Object> chargePayload = Map.of(
                "orderId", saved.getId(),
                "amount", saved.getTotalAmount() != null ? saved.getTotalAmount() : BigDecimal.valueOf(299.00)
        );
        paymentGatewayClient.chargeOrder(chargePayload);
        saved.confirmPaymentCaptured();
        OrderEntity completed = orderRepository.save(saved);
        orderEventPublisher.publishOrderLifecycleEvent(completed.getOrderNumber(), completed.getStatus().name());
        return completed;
    }
}
