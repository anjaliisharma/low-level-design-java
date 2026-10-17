# UML interview guide

Open `uber-lld-class-diagram.puml` in IntelliJ with a PlantUML plugin, or paste it into any PlantUML renderer.

## How to explain this in an interview

Start with the domain: a `Rider` requests a `Trip`; a `Driver` fulfills it; `Location`, `Rating`, and `TripStatus` are supporting value/state types.

Then explain orchestration: `TripService` owns the trip-creation workflow. It creates `TripMetadata`, which intentionally carries every input a decision rule may need.

Finally explain extensibility: `PricingStrategy` and `DriverMatchingStrategy` are interfaces. New rules such as surge pricing, premium-driver matching, or zone-based matching can be added without changing `TripService` (Open/Closed Principle). The dependencies are supplied through its constructor, so it depends on abstractions rather than global singletons.

## Interview follow-ups worth mentioning

- `DriverManager` uses an in-memory map only for the demo. Production needs persistence and a geospatial index.
- Driver assignment needs an atomic reservation or a database transaction; otherwise two concurrent requests can choose the same driver.
- Distance is a stand-in for ETA. A real system uses maps, traffic, and driver acceptance.
- Trip states should have guarded transitions; an ended trip should not be startable again.
