package com.learning.uber.model;

public final class TripMetadata {
    private final Location source;
    private final Location destination;
    private final Rating riderRating;
    private Rating driverRating;
    public TripMetadata(Location source, Location destination, Rating riderRating) { this.source = source; this.destination = destination; this.riderRating = riderRating; }
    public Location getSource() { return source; }
    public Location getDestination() { return destination; }
    public Rating getRiderRating() { return riderRating; }
    public Rating getDriverRating() { return driverRating; }
    public void setDriverRating(Rating driverRating) { this.driverRating = driverRating; }
    public double tripDistance() { return source.distanceTo(destination); }
}
