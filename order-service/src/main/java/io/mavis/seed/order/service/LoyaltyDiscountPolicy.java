package io.mavis.seed.order.service;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class LoyaltyDiscountPolicy {

    public BigDecimal applyTierDiscount(BigDecimal baseAmount, int orderCount) {
        if (orderCount >= 10) {
            return baseAmount.multiply(BigDecimal.valueOf(0.85));
        } else if (orderCount >= 3) {
            return baseAmount.multiply(BigDecimal.valueOf(0.92));
        }
        return baseAmount;
    }
}
