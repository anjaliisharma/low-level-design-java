package com.learning.uber.strategy;

import com.learning.uber.model.TripMetadata;
import java.math.BigDecimal;

/** Example alternative strategy: gives highly rated riders a discount. */
public final class RatingBasedPricingStrategy implements PricingStrategy {
    @Override public BigDecimal calculatePrice(TripMetadata metadata) {
        return metadata.getRiderRating().isHighRating() ? BigDecimal.valueOf(55) : BigDecimal.valueOf(65);
    }
}
