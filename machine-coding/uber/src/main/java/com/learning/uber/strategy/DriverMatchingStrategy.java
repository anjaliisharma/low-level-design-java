package com.learning.uber.strategy;

import com.learning.uber.model.Driver;
import com.learning.uber.model.TripMetadata;

public interface DriverMatchingStrategy {
    Driver matchDriver(TripMetadata metadata);
}
