package io.mavis.seed.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "catalog-service", url = "http://catalog-service:8080")
public interface CatalogInventoryClient {

    @GetMapping("/api/products/{id}")
    Map<String, Object> fetchProduct(@PathVariable("id") Long id);

    @PutMapping("/api/products/{id}/stock")
    Map<String, Object> reserveStock(@PathVariable("id") Long id, @RequestBody Map<String, Object> reservation);
}
