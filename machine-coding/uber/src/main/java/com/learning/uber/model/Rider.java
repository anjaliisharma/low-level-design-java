package com.learning.uber.model;

import java.util.Objects;

public final class Rider {
    private final String name;
    private final Rating rating;
    public Rider(String name, Rating rating) { this.name = Objects.requireNonNull(name); this.rating = Objects.requireNonNull(rating); }
    public String getName() { return name; }
    public Rating getRating() { return rating; }
}
