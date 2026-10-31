package io.mavis.seed.payment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "mavis_payments")
public class PaymentTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transactionRef;
    private Long orderId;
    private BigDecimal amount;
    private String provider;
    private int riskScore;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    public PaymentTransactionEntity() {
        this.status = PaymentStatus.PENDING;
    }

    public void authorizePayment() {
        if (this.status == PaymentStatus.PENDING) {
            this.status = PaymentStatus.AUTHORIZED;
        }
    }

    public void captureFunds() {
        if (this.status == PaymentStatus.AUTHORIZED) {
            this.status = PaymentStatus.CAPTURED;
        }
    }

    public void refundTransaction() {
        if (this.status == PaymentStatus.CAPTURED) {
            this.status = PaymentStatus.REFUNDED;
        }
    }

    public Long getId() { return id; }
    public String getTransactionRef() { return transactionRef; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public int getRiskScore() { return riskScore; }
    public void setRiskScore(int riskScore) { this.riskScore = riskScore; }
    public PaymentStatus getStatus() { return status; }
}
