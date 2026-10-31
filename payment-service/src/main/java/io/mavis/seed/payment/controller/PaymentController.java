package io.mavis.seed.payment.controller;

import io.mavis.seed.payment.domain.PaymentTransactionEntity;
import io.mavis.seed.payment.repository.PaymentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @GetMapping
    public List<PaymentTransactionEntity> listPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping("/order/{orderId}")
    public PaymentTransactionEntity getPaymentByOrder(@PathVariable("orderId") Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseGet(PaymentTransactionEntity::new);
    }

    @PostMapping("/charge")
    public PaymentTransactionEntity chargeOrder(@RequestBody PaymentTransactionEntity tx) {
        tx.authorizePayment();
        tx.captureFunds();
        return paymentRepository.save(tx);
    }
}
