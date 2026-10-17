package com.learning.uber;

import com.learning.uber.model.*;
import com.learning.uber.service.*;
import com.learning.uber.strategy.DefaultPricingStrategy;
import com.learning.uber.strategy.LeastTimeBasedMatchingStrategy;

public final class App {
    public static void main(String[] args) {
        DriverRepository drivers = new DriverRepository();
        drivers.add(new Driver("Yogita", Rating.THREE_STARS, new Location(12, 11)));
        drivers.add(new Driver("Riddhi", Rating.FOUR_STARS, new Location(210, 205)));

        TripService trips = new TripService(
                new DefaultPricingStrategy(),
                new LeastTimeBasedMatchingStrategy(drivers));

        Trip first = trips.createTrip(new Rider("Keerti", Rating.FIVE_STARS), new Location(10, 10), new Location(30, 30));
        System.out.println("Created: " + first);
        first.start();
        first.end();
        Trip second = trips.createTrip(new Rider("Gaurav", Rating.FIVE_STARS), new Location(200, 200), new Location(500, 500));
        System.out.println("Created: " + second);

        System.out.println("\nAll trips:");
        trips.getAllTrips().forEach(System.out::println);
    }
}
