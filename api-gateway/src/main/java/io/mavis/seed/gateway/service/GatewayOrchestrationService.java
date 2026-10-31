package io.mavis.seed.gateway.service;

import io.mavis.seed.gateway.client.CatalogServiceClient;
import io.mavis.seed.gateway.client.OrderServiceClient;
import io.mavis.seed.gateway.client.PaymentServiceClient;
import io.mavis.seed.gateway.dto.CheckoutCommand;
import io.mavis.seed.gateway.dto.OrderAggregateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GatewayOrchestrationService {

    private final OrderServiceClient orderServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final PaymentServiceClient paymentServiceClient;

    public GatewayOrchestrationService(
            OrderServiceClient orderServiceClient,
            CatalogServiceClient catalogServiceClient,
            PaymentServiceClient paymentServiceClient) {
        this.orderServiceClient = orderServiceClient;
        this.catalogServiceClient = catalogServiceClient;
        this.paymentServiceClient = paymentServiceClient;
    }

    public List<Map<String, Object>> fetchCatalogProducts() {
        return catalogServiceClient.listProducts();
    }

    public List<OrderAggregateDto> fetchAllOrders() {
        return orderServiceClient.listOrders();
    }

    public OrderAggregateDto fetchOrderDetails(Long id) {
        OrderAggregateDto order = orderServiceClient.getOrderById(id);
        paymentServiceClient.getPaymentForOrder(id);
        return order;
    }

    public OrderAggregateDto executeCheckout(CheckoutCommand command) {
        if (command.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        catalogServiceClient.getProductById(command.getProductId());
        return orderServiceClient.createOrder(command);
    }
}
