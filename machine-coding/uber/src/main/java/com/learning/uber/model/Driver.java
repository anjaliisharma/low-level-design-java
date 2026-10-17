package com.learning.uber.model;

import java.util.Objects;

public final class Driver {
    private final String name;
    private final Rating rating;
    private Location currentLocation;
    private boolean available = true;
    public Driver(String name, Rating rating, Location currentLocation) {
        this.name = Objects.requireNonNull(name); this.rating = Objects.requireNonNull(rating); this.currentLocation = Objects.requireNonNull(currentLocation);
    }
    public String getName() { return name; }
    public Rating getRating() { return rating; }
    public Location getCurrentLocation() { return currentLocation; }
    public boolean isAvailable() { return available; }
    public void assign() { if (!available) throw new IllegalStateException(name + " is unavailable"); available = false; }
    public void completeAt(Location location) { currentLocation = location; available = true; }
}
