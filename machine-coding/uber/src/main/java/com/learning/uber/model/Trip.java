package com.learning.uber.model;

import java.math.BigDecimal;

public final class Trip {
    private final long id;
    private final Rider rider;
    private final Driver driver;
    private final Location source;
    private final Location destination;
    private final BigDecimal price;
    private TripStatus status = TripStatus.DRIVER_ON_THE_WAY;
    public Trip(long id, Rider rider, Driver driver, Location source, Location destination, BigDecimal price) {
        this.id = id; this.rider = rider; this.driver = driver; this.source = source; this.destination = destination; this.price = price;
    }
    public long getId() { return id; }
    public TripStatus getStatus() { return status; }
    public void start() { status = TripStatus.STARTED; }
    public void end() { status = TripStatus.ENDED; driver.completeAt(destination); }
    @Override public String toString() {
        return "Trip %d | rider=%s | driver=%s | price=%s | route=%s -> %s | status=%s".formatted(id, rider.getName(), driver.getName(), price, source, destination, status);
    }
}
