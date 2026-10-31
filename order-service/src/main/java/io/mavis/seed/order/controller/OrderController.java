package io.mavis.seed.order.controller;

import io.mavis.seed.order.domain.OrderEntity;
import io.mavis.seed.order.service.OrderFulfillmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderFulfillmentService fulfillmentService;

    public OrderController(OrderFulfillmentService fulfillmentService) {
        this.fulfillmentService = fulfillmentService;
    }

    @GetMapping
    public List<OrderEntity> listOrders() {
        return fulfillmentService.listAllOrders();
    }

    @GetMapping("/{id}")
    public OrderEntity getOrder(@PathVariable("id") Long id) {
        return fulfillmentService.findOrder(id);
    }

    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order) {
        return fulfillmentService.createAndChargeOrder(order);
    }

    @PutMapping("/{id}/status")
    public OrderEntity advanceOrderStatus(@PathVariable("id") Long id) {
        OrderEntity order = fulfillmentService.findOrder(id);
        order.markOrderShipped();
        return order;
    }
}
