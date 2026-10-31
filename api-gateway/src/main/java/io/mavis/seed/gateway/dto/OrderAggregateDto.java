package io.mavis.seed.gateway.dto;

import java.math.BigDecimal;

public class OrderAggregateDto {
    private Long orderId;
    private String orderNumber;
    private String customerEmail;
    private String productSku;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String paymentStatus;

    public OrderAggregateDto() {}

    public OrderAggregateDto(Long orderId, String orderNumber, String customerEmail, String productSku, BigDecimal totalAmount, String orderStatus, String paymentStatus) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.customerEmail = customerEmail;
        this.productSku = productSku;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    public Long getOrderId() { return orderId; }
    public String getOrderNumber() { return orderNumber; }
    public String getCustomerEmail() { return customerEmail; }
    public String getProductSku() { return productSku; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getOrderStatus() { return orderStatus; }
    public String getPaymentStatus() { return paymentStatus; }
}
