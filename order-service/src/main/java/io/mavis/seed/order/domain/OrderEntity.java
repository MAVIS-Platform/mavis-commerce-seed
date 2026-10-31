package io.mavis.seed.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mavis_orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String customerEmail;
    private Long productId;
    private int quantity;
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @OneToMany
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {
        this.status = OrderStatus.CREATED;
    }

    public void beginPaymentReservation() {
        if (this.status == OrderStatus.CREATED) {
            this.status = OrderStatus.PAYMENT_PENDING;
        }
    }

    public void confirmPaymentCaptured() {
        if (this.status == OrderStatus.PAYMENT_PENDING) {
            this.status = OrderStatus.PAID;
        }
    }

    public void markOrderShipped() {
        if (this.status == OrderStatus.PAID) {
            this.status = OrderStatus.SHIPPED;
        }
    }

    public void cancelUnpaidOrder() {
        if (this.status == OrderStatus.CREATED) {
            this.status = OrderStatus.CANCELLED;
        }
    }

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public OrderStatus getStatus() { return status; }
    public List<OrderItemEntity> getItems() { return items; }
}
