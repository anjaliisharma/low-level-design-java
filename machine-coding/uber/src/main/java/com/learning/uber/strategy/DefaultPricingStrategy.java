package com.learning.uber.strategy;

import com.learning.uber.model.TripMetadata;
import java.math.BigDecimal;
import java.math.RoundingMode;

/** Simple distance pricing: base fare + per-unit distance fare. */
public final class DefaultPricingStrategy implements PricingStrategy {
    @Override public BigDecimal calculatePrice(TripMetadata metadata) {
        return BigDecimal.valueOf(50 + metadata.tripDistance() * 10)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
