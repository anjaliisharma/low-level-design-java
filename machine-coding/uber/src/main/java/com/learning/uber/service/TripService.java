package com.learning.uber.service;

import com.learning.uber.model.*;
import com.learning.uber.strategy.DriverMatchingStrategy;
import com.learning.uber.strategy.PricingStrategy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Coordinates the use case; it does not decide matching or pricing rules itself. */
public final class TripService {
    private final PricingStrategy pricingStrategy;
    private final DriverMatchingStrategy matchingStrategy;
    private final List<Trip> trips = new ArrayList<>();
    private long nextTripId = 1;

    public TripService(PricingStrategy pricingStrategy, DriverMatchingStrategy matchingStrategy) {
        this.pricingStrategy = pricingStrategy;
        this.matchingStrategy = matchingStrategy;
    }

    public Trip createTrip(Rider rider, Location source, Location destination) {
        TripMetadata metadata = new TripMetadata(source, destination, rider.getRating());
        Driver driver = matchingStrategy.matchDriver(metadata);
        BigDecimal price = pricingStrategy.calculatePrice(metadata);
        driver.assign();

        Trip trip = new Trip(nextTripId++, rider, driver, source, destination, price);
        trips.add(trip);
        return trip;
    }

    public List<Trip> getAllTrips() {
        return List.copyOf(trips);
    }
}
