package io.mavis.seed.gateway.controller;

import io.mavis.seed.gateway.dto.CheckoutCommand;
import io.mavis.seed.gateway.dto.OrderAggregateDto;
import io.mavis.seed.gateway.service.GatewayOrchestrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gateway")
public class GatewayController {

    private final GatewayOrchestrationService orchestrationService;

    public GatewayController(GatewayOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @GetMapping("/products")
    public List<Map<String, Object>> getProducts() {
        return orchestrationService.fetchCatalogProducts();
    }

    @GetMapping("/orders")
    public List<OrderAggregateDto> getOrders() {
        return orchestrationService.fetchAllOrders();
    }

    @GetMapping("/orders/{id}")
    public OrderAggregateDto getOrderById(@PathVariable("id") Long id) {
        return orchestrationService.fetchOrderDetails(id);
    }

    @PostMapping("/checkout")
    public OrderAggregateDto checkout(@RequestBody CheckoutCommand command) {
        return orchestrationService.executeCheckout(command);
    }
}
