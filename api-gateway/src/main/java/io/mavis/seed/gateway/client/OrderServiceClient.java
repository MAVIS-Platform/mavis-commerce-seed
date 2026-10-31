package io.mavis.seed.gateway.client;

import io.mavis.seed.gateway.dto.CheckoutCommand;
import io.mavis.seed.gateway.dto.OrderAggregateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "order-service", url = "http://order-service:8080")
public interface OrderServiceClient {

    @GetMapping("/api/orders")
    List<OrderAggregateDto> listOrders();

    @GetMapping("/api/orders/{id}")
    OrderAggregateDto getOrderById(@PathVariable("id") Long id);

    @PostMapping("/api/orders")
    OrderAggregateDto createOrder(@RequestBody CheckoutCommand command);
}
