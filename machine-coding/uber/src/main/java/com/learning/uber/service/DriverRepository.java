package com.learning.uber.service;

import com.learning.uber.model.Driver;
import java.util.ArrayList;
import java.util.List;

/** In-memory data access for the demo. Replace with a database implementation in production. */
public final class DriverRepository {
    private final List<Driver> drivers = new ArrayList<>();

    public void add(Driver driver) {
        drivers.add(driver);
    }

    public List<Driver> findAvailable() {
        return drivers.stream().filter(Driver::isAvailable).toList();
    }
}
