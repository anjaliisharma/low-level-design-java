# Uber/Ola Low-Level Design — Java

A small, runnable Java port inspired by Keerti Purswani's C++ Uber/Ola LLD repository. It is intended for learning object-oriented design patterns, not for production dispatching.

## Concepts demonstrated

| Concept | Java implementation |
| --- | --- |
| Strategy pattern | `PricingStrategy` and `DriverMatchingStrategy` |
| Dependency injection | `App` supplies concrete strategies and repository to `TripService` |
| Repository pattern | `DriverRepository` owns in-memory driver storage |
| Domain model | `Rider`, `Driver`, `Location`, `Trip`, `TripMetadata` |
| State | `TripStatus`; a driver becomes unavailable when assigned |

## Project layout

```
src/main/java/com/learning/uber/
  App.java                 # runnable walkthrough
  model/                   # rider, driver, trip, location
  strategy/                # interchangeable pricing and matching rules
  service/                 # managers/orchestration
```

## Run

Requires JDK 17 or later.

```powershell
cd C:\Users\anjal\Downloads\Uber-Ola-Low-Level-Design-Java
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out com.learning.uber.App
```

## How a trip is created

`TripService.createTrip()` builds `TripMetadata`, uses its injected matching and pricing strategies, assigns a driver, then stores the resulting trip. To experiment, pass a different strategy to the `TripService` constructor or add a new implementation of a strategy interface.

## Deliberate simplifications

There is no database, GPS stream, payment system, maps API, or concurrent dispatch. In a real system, driver assignment needs locking/transactions and a geospatial index such as a quadtree or geohash.
