package com.learning.uber.strategy;

import com.learning.uber.model.Driver;
import com.learning.uber.model.TripMetadata;
import com.learning.uber.service.DriverRepository;
import java.util.Comparator;

/** Uses straight-line distance as a teaching-friendly stand-in for ETA. */
public final class LeastTimeBasedMatchingStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;

    public LeastTimeBasedMatchingStrategy(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override public Driver matchDriver(TripMetadata metadata) {
        Driver driver = driverRepository.findAvailable().stream()
            .min(Comparator.comparingDouble(d -> d.getCurrentLocation().distanceTo(metadata.getSource())))
            .orElseThrow(() -> new IllegalStateException("No available drivers for this trip"));
        metadata.setDriverRating(driver.getRating());
        return driver;
    }
}
