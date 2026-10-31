package io.mavis.seed.catalog.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "mavis_products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String name;
    private String categoryCode;
    private BigDecimal price;
    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.DRAFT;

    @ManyToOne
    private CategoryEntity category;

    public ProductEntity() {
        this.status = ProductStatus.DRAFT;
    }

    public void publishProduct() {
        if (this.status == ProductStatus.DRAFT) {
            this.status = ProductStatus.ACTIVE;
        }
    }

    public void markDepleted() {
        if (this.status == ProductStatus.ACTIVE) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void replenishInventory(int addedUnits) {
        if (this.status == ProductStatus.OUT_OF_STOCK) {
            this.stockQuantity = this.stockQuantity + addedUnits;
            this.status = ProductStatus.ACTIVE;
        }
    }

    public void archiveProduct() {
        if (this.status == ProductStatus.OUT_OF_STOCK) {
            this.status = ProductStatus.ARCHIVED;
        }
    }

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategoryCode() { return categoryCode; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public ProductStatus getStatus() { return status; }
    public CategoryEntity getCategory() { return category; }
}
