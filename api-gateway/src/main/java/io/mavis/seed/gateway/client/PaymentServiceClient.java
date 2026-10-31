package io.mavis.seed.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "payment-service", url = "http://payment-service:8080")
public interface PaymentServiceClient {

    @GetMapping("/api/payments/order/{orderId}")
    Map<String, Object> getPaymentForOrder(@PathVariable("orderId") Long orderId);
}
