package com.learning.uber.model;

public final class Location {
    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double distanceTo(Location other) {
        double lat = latitude - other.latitude;
        double lon = longitude - other.longitude;
        return Math.sqrt(lat * lat + lon * lon);
    }
    @Override public String toString() { return "(" + latitude + ", " + longitude + ")"; }
}
