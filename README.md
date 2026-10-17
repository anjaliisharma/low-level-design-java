# Low-Level Design in Java

A structured collection of low-level design, object-oriented design, design-pattern, data-structure, and machine-coding exercises implemented in Java.

The repository is intended for software-engineering interview preparation and hands-on practice with maintainable object-oriented systems.

## Repository structure

```text
low-level-design-java/
|-- design-principles/
|   `-- solid-principles/
|-- design-patterns/
|   |-- behavioral/
|   |-- creational/
|   `-- structural/
|-- data-structures/
|   |-- hash-map/
|   `-- heap/
`-- machine-coding/
    |-- amazon-locker/
    |-- atm/
    |-- customer-issue-resolution/
    |-- elevator/
    |-- logger/
    |-- rate-limiter/
    |-- snakes-and-ladders/
    |-- splitwise/
    |-- tic-tac-toe/
    |-- uber/
    `-- vending-machine/
```

## Contents

### Design principles

| Topic | Focus |
| --- | --- |
| [SOLID principles](design-principles/solid-principles) | SRP, OCP, LSP, ISP, and DIP examples |

### Design patterns

| Topic | Focus |
| --- | --- |
| [Behavioral patterns](design-patterns/behavioral) | Communication and responsibility between objects |
| [Creational patterns](design-patterns/creational) | Flexible object-creation techniques |
| [Structural patterns](design-patterns/structural) | Composition of classes and objects |

### Data structures

| Project | Focus |
| --- | --- |
| [Hash map](data-structures/hash-map) | Hashing and collision handling |
| [Heap](data-structures/heap) | Heap operations and priority-based access |

### Machine-coding exercises

| Project | Domain |
| --- | --- |
| [Amazon Locker](machine-coding/amazon-locker) | Package allocation, pickup, and locker management |
| [ATM](machine-coding/atm) | Transactions, state, and cash dispensing |
| [Customer Issue Resolution](machine-coding/customer-issue-resolution) | Issue assignment and resolution workflow |
| [Elevator](machine-coding/elevator) | Elevator state and request scheduling |
| [Logger](machine-coding/logger) | Logging levels and extensible output behavior |
| [Rate Limiter](machine-coding/rate-limiter) | Request throttling strategies |
| [Snakes and Ladders](machine-coding/snakes-and-ladders) | Board-game entities and turn management |
| [Splitwise](machine-coding/splitwise) | Expense splitting and balance tracking |
| [Tic-Tac-Toe](machine-coding/tic-tac-toe) | Game state and winning strategies |
| [Uber](machine-coding/uber) | Ride booking and driver matching |
| [Vending Machine](machine-coding/vending-machine) | Inventory, payments, and machine state |

## Running the examples

The collection contains both Maven projects and plain Java/IntelliJ projects.

For a Maven-based project, enter the project directory and run:

```bash
mvn test
```

or:

```bash
mvn package
```

For a plain Java project, open the project directory in IntelliJ IDEA and run its `Main` class. Generated IDE metadata and compiled output are intentionally excluded from version control.

## Learning goals

- Translate requirements into classes, interfaces, and interactions.
- Apply SOLID principles and common design patterns.
- Keep domain logic separate from presentation and infrastructure concerns.
- Evaluate extensibility, testability, and trade-offs in each design.
- Practice explaining designs during machine-coding and LLD interviews.

## Future improvements

- Add concise problem statements and assumptions to every project.
- Add UML or Mermaid class diagrams.
- Standardize all projects on Maven.
- Expand unit-test coverage.
- Document alternative designs and their trade-offs.

