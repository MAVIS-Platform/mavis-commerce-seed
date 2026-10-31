package io.mavis.seed.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "catalog-service", url = "http://catalog-service:8080")
public interface CatalogServiceClient {

    @GetMapping("/api/products")
    List<Map<String, Object>> listProducts();

    @GetMapping("/api/products/{id}")
    Map<String, Object> getProductById(@PathVariable("id") Long id);
}
