package com.learning.uber.strategy;

import com.learning.uber.model.TripMetadata;
import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(TripMetadata metadata);
}
