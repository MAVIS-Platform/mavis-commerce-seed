package io.mavis.seed.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "payment-service", url = "http://payment-service:8080")
public interface PaymentGatewayClient {

    @PostMapping("/api/payments/charge")
    Map<String, Object> chargeOrder(@RequestBody Map<String, Object> paymentRequest);
}
