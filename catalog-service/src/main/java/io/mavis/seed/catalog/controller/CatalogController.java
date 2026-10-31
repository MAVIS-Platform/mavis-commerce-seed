package io.mavis.seed.catalog.controller;

import io.mavis.seed.catalog.domain.ProductEntity;
import io.mavis.seed.catalog.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class CatalogController {

    private final ProductRepository productRepository;

    public CatalogController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> listProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductEntity getProduct(@PathVariable("id") Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @PostMapping
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        product.publishProduct();
        return productRepository.save(product);
    }

    @PutMapping("/{id}/stock")
    public ProductEntity adjustStock(@PathVariable("id") Long id, @RequestBody Map<String, Object> body) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        product.replenishInventory(10);
        return productRepository.save(product);
    }
}
