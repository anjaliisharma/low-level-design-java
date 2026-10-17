# Creational Design Patterns in Java

An interview-ready, runnable study project for the five **Gang of Four (GoF) creational patterns**. Every module uses the same notification-campaign domain so you can focus on what changes: **who creates the object, what is being created, and why**.

## The one-minute mental model

Creational patterns hide or organize object creation when direct construction (`new`) would couple code too tightly or make construction difficult.

| Pattern | Remember it as | Best signal in a problem statement |
|---|---|---|
| Factory Method | A subclass chooses **one product** | “Let implementations decide which object to create.” |
| Abstract Factory | A factory creates a **matching family** | “Switch a whole family without mixing incompatible members.” |
| Builder | Assemble **one complex object step by step** | “Many optional parameters; readable, validated construction.” |
| Prototype | Create by **copying an existing configured object** | “Construction is expensive or configuration is mostly repeated.” |
| Singleton | Guarantee **one shared instance per JVM/class loader** | “Exactly one coordinator is required.” Use sparingly. |

> Memory hook: **method chooses one; factory chooses a family; builder assembles; prototype copies; singleton shares one.**

## Project layout

```text
creational-design-patterns/
├── pom.xml                   # parent Maven aggregator
├── factory-method/           # Notification selected by campaign subclass
├── abstract-factory/         # Matching template + delivery gateway
├── builder/                  # Immutable NotificationRequest
├── prototype/                # Copyable campaign + prototype registry
└── singleton/                # JVM-wide notification metrics
```

Each module contains:

- a small `main` method that can be shown in an interview;
- production code with meaningful roles;
- a focused JUnit 5 test demonstrating the promise of the pattern.

## Prerequisites and commands

- JDK 17+
- Maven 3.9+

From the project root:

```bash
mvn clean test
```

Run a particular demo after compiling (replace the module/class as needed):

```bash
mvn -pl factory-method compile exec:java \
  -Dexec.mainClass=com.example.creational.factorymethod.FactoryMethodDemo
```

You can also open the root `pom.xml` directly in IntelliJ IDEA or Eclipse and run any `*Demo.main()`.

---

## 1. Factory Method

**Intent:** define an operation for creating a product, while allowing subclasses to decide which concrete product is created.

In this project:

```text
NotificationCampaign (Creator)
  ├── EmailCampaign ──creates──> EmailNotification
  └── SmsCampaign   ──creates──> SmsNotification
                                  implements Notification (Product)
```

`NotificationCampaign.notify()` contains the stable workflow. It calls the overridable `createNotification()` factory method rather than constructing a concrete notification itself.

```java
public abstract class NotificationCampaign {
    public final String notify(String recipient, String message) {
        Notification notification = createNotification();
        return notification.send(recipient, message);
    }

    protected abstract Notification createNotification();
}
```

Look at `factory-method/.../NotificationCampaign.java`, then `EmailCampaign.java` and `SmsCampaign.java`.

### When to use it

- A base workflow should work with different product implementations.
- A framework exposes extension points to subclasses/plugins.
- The exact type is unknown until configuration or runtime.

### Advantages and costs

- Removes concrete product construction from the main workflow.
- Supports Open/Closed Principle: add another campaign/product pair without changing the workflow.
- Usually introduces parallel creator subclasses and more files.
- Inheritance can be excessive when a simple injected factory function would suffice.

### Interview questions

**Q: Factory Method vs Simple Factory?**  
A Simple Factory is usually one class/static method with a conditional that returns a product; it is useful but not a GoF pattern. Factory Method relies on polymorphism—typically subclasses override a creation method. A growing `switch` in a simple factory often signals that a polymorphic design may help.

**Q: Factory Method vs Abstract Factory?**  
Factory Method normally creates one product through inheritance. Abstract Factory creates a related family of products through composition. An Abstract Factory can internally use several factory methods.

**Q: Why is the creator useful if it only delegates creation?**  
The creator usually owns business logic around the product. Here, `notify()` is stable while product creation varies.

**Q: Must Factory Method use an abstract class?**  
No. An interface default method, overridable concrete method, or injected `Supplier<Product>` can express similar intent. The classic GoF form uses inheritance.

**Q: What SOLID principles does it support?**  
Open/Closed and Dependency Inversion: the workflow depends on the `Notification` abstraction, not a concrete channel.

**30-second interview answer:** “Factory Method moves creation behind a polymorphic method. The creator keeps the stable workflow, while subclasses select the concrete product. I use it when implementations should extend creation behavior without changing the workflow.”

---

## 2. Abstract Factory

**Intent:** create families of related objects without exposing their concrete classes.

In this project, an email family contains an email template and SMTP gateway; an SMS family contains an SMS template and SMS gateway:

```text
NotificationFactory
  ├── EmailNotificationFactory -> Email template + SMTP gateway
  └── SmsNotificationFactory   -> SMS template   + SMS gateway

NotificationService consumes only MessageTemplate + DeliveryGateway.
```

The client receives one factory and cannot accidentally ask the service to construct an email template with an SMS gateway.

### When to use it

- Products are designed to work as a family (Windows UI widgets, AWS resources, database-driver components).
- You must switch an entire product family using configuration.
- Client code should not know concrete classes.

### Advantages and costs

- Makes product-family consistency explicit.
- Swapping families is easy: inject a different factory.
- Adding a new family is easy.
- Adding a new *kind of product* is costly because every factory interface/implementation must change.

### Interview questions

**Q: Abstract Factory vs Factory Method?**  
Abstract Factory is object composition and produces multiple related product types. Factory Method is commonly inheritance and produces one product. They are often used together.

**Q: Abstract Factory vs Builder?**  
An Abstract Factory immediately returns related products. A Builder constructs one complex product over multiple steps and may offer different representations.

**Q: What happens when a third product is added to the family?**  
The abstract factory contract and every concrete factory must be updated. This is the pattern’s main tradeoff: adding families is easy; adding product types is hard.

**Q: How does dependency injection relate?**  
A DI container can select and provide the concrete factory. DI is a broader dependency-management technique; Abstract Factory is the design of the family-creation API.

**Q: Can incompatible products still be mixed?**  
The basic Java type system shown here does not make mixing impossible outside the factory. Package boundaries, generics, sealed types, or not exposing constructors can strengthen the guarantee.

**30-second interview answer:** “Abstract Factory provides an interface for creating a compatible family of products. The client accepts one factory and works only with product interfaces. It is ideal when I need to swap families, though adding a new product type changes all factories.”

---

## 3. Builder

**Intent:** separate and clarify the step-by-step construction of a complex object.

`NotificationRequest` has required fields (`recipient`, `body`), optional fields, defaults, validation, and immutable collection snapshots.

```java
NotificationRequest request = NotificationRequest
        .builder("candidate@example.com", "Your interview is tomorrow")
        .subject("Interview reminder")
        .priority(NotificationRequest.Priority.HIGH)
        .addAttachment("interview-guide.pdf")
        .metadata("campaign", "interview-prep")
        .build();
```

This avoids a telescoping constructor such as:

```java
new NotificationRequest(to, subject, body, null, true, null, attachments, metadata);
```

### When to use it

- A constructor has many optional parameters, especially several of the same type.
- An immutable object needs staged construction.
- Construction needs defaults, validation, or named fluent steps.
- Different representations can be produced from the same construction process (the classic GoF form may include a Director).

### Advantages and costs

- Call sites are readable and resistant to argument-order bugs.
- The final product can be immutable and valid at creation.
- Construction logic and defaults have one home.
- Adds boilerplate; records or static factories are often better for small value objects.

### Interview questions

**Q: Why not setters?**  
Setters expose partially initialized, mutable objects. A builder gathers state and validates once before returning a complete object.

**Q: Where should validation happen?**  
Validate individual values in builder methods when useful for fast feedback, and cross-field invariants in `build()`. The product constructor should still protect its invariants if other construction paths exist.

**Q: Is the built object automatically immutable?**  
No. Make fields final, provide no mutators, and defensively copy mutable inputs and outputs. This project uses `List.copyOf` and `Map.copyOf`.

**Q: Is a Director required?**  
Not for the common fluent Java builder. A Director is useful when named, reusable construction recipes are valuable.

**Q: Builder vs Factory?**  
A factory chooses *which object* and usually returns it in one call. A builder controls *how one complex object is assembled* across several calls.

**Q: What is a staged/step builder?**  
It uses multiple interfaces and return types so the compiler forces required steps in order. It gives stronger guarantees but adds considerable complexity.

**30-second interview answer:** “Builder gives a readable, step-by-step API for creating a complex object, especially an immutable one with required and optional fields. It avoids telescoping constructors and centralizes defaults and validation.”

---

## 4. Prototype

**Intent:** create new objects by copying a configured prototype rather than rebuilding from scratch.

The registry stores defensive copies of campaign templates and returns a fresh copy for each request:

```java
registry.register("interview", configuredCampaign);
NotificationCampaign copy = registry.create("interview");
copy.setSubject("Interview rescheduled");
```

The explicit copy constructor deep-copies the mutable recipient list. Changing a copy therefore cannot change the original.

### When to use it

- Creating/configuring an object is expensive.
- New objects differ only slightly from known templates.
- Concrete classes should be hidden while objects are created dynamically.
- A runtime catalog/registry of templates is useful.

### Advantages and costs

- Avoids repeated expensive configuration.
- Can add prototypes at runtime without a creator-class hierarchy.
- Correct deep copying is difficult for complex object graphs, cycles, identity, and external resources.
- Copy semantics must be documented: which state is shared, reset, or duplicated?

### Interview questions

**Q: Shallow copy vs deep copy?**  
A shallow copy duplicates the outer object but shares referenced mutable objects. A deep copy recursively duplicates mutable owned state. Immutable values may safely be shared.

**Q: Why avoid Java `Cloneable`/`Object.clone()`?**  
`Cloneable` has awkward semantics, `clone()` is shallow by default, constructors are bypassed, checked exceptions leak into callers, and its contract is easy to misuse. Copy constructors or named `copy()` methods are clearer.

**Q: Prototype vs Builder?**  
Prototype starts from existing state and changes a few values. Builder starts from construction inputs and assembles the object. They can work together: clone a baseline, then use a builder to customize it.

**Q: How do you copy database connections, files, or threads?**  
Usually you do not. Define explicit semantics: share a managed reference, reopen the resource, or exclude/reset it in the copy.

**Q: How do you handle cyclic graphs?**  
Use an identity map from original objects to copies while traversing so each object is copied once and cycles reconnect correctly.

**30-second interview answer:** “Prototype creates objects by copying a preconfigured instance. It is useful when setup is expensive or instances vary only slightly. The important design decision is explicit shallow-versus-deep copy semantics.”

---

## 5. Singleton

**Intent:** guarantee one accessible instance and control its lifecycle.

This project uses an enum singleton for application-wide notification metrics:

```java
public enum NotificationMetrics {
    INSTANCE;
    // thread-safe mutable state
}
```

Enum singletons are concise, serialization-safe, and resistant to ordinary reflection attacks. `ConcurrentHashMap` and `LongAdder` make the singleton’s *mutable state* safe under concurrency—singleton construction alone does not provide that.

### When to use it

- Exactly one instance is a genuine invariant, not merely a convenience.
- A process-wide coordinator owns a unique resource.
- Prefer a DI-container singleton scope when possible because lifecycle and substitution stay explicit.

### Advantages and costs

- One instance with controlled initialization.
- Easy shared access.
- Global state hides dependencies, couples tests, complicates parallelism, and can become a concurrency bottleneck.
- “One” means one per class loader/JVM, not one across a distributed system.

### Interview questions

**Q: What is the safest Java implementation?**  
An enum singleton is the simplest robust choice. For lazy non-enum creation, the initialization-on-demand holder idiom is also safe because class initialization is synchronized by the JVM.

**Q: Why is naive lazy initialization unsafe?**  
Two threads may both observe `instance == null` and create different instances. Correct double-checked locking requires a `volatile` field; without it, publication/reordering is unsafe.

**Q: Does one instance make its fields thread-safe?**  
No. Instance uniqueness and thread safety are separate. Mutable fields still need immutability, locks, atomics, or concurrent collections.

**Q: Can reflection or serialization break Singleton?**  
They can break many class-based implementations. Serialization needs `readResolve()`; reflection can invoke private constructors. Enums handle these cases best under normal JVM rules.

**Q: Singleton vs static class?**  
A singleton is an object: it can implement interfaces, be passed as a dependency, and potentially be substituted. Static methods cannot. Both can still create global coupling.

**Q: Singleton vs dependency injection singleton scope?**  
DI manages one instance within a container scope and makes dependencies explicit. It is generally easier to test and control. A GoF singleton enforces access through the class itself.

**Q: Is a Singleton global across microservices?**  
No. It is generally one per class loader in one JVM. Cluster-wide uniqueness requires distributed coordination such as leader election, a database constraint, or a distributed lock.

**30-second interview answer:** “Singleton guarantees one instance and a global access point, but I use it cautiously because it creates hidden global state. In Java, an enum is robust; and I still must make mutable state thread-safe. With DI, I usually prefer container-managed singleton scope.”

---

## Fast comparison: what changes when requirements change?

| Requirement change | Best fit | Why |
|---|---|---|
| Add WhatsApp as another independently selectable channel | Factory Method | Add a product and creator without changing the workflow. |
| Add a Push family containing formatter, sender, and tracker | Abstract Factory | All related products switch together. |
| Add optional retry policy and tracking metadata | Builder | Fluent optional construction stays readable. |
| Create 10,000 campaigns based on configured templates | Prototype | Copy existing setup rather than rebuild it. |
| Keep one in-process metrics registry | Singleton (cautiously) | Unique process-wide ownership is the requirement. |

## How to answer an open-ended design-pattern interview question

1. **State the problem before naming the pattern.** “Object construction is coupled to the workflow.”
2. **Name the roles.** Creator/product, factory/product families, builder/product, prototype, or singleton.
3. **Sketch the smallest code.** Prefer interfaces plus two implementations over a large framework.
4. **Explain the extension path.** Say what changes when a new channel is added.
5. **Name the tradeoff.** Every pattern buys flexibility with indirection, classes, or lifecycle complexity.
6. **Mention the simpler alternative.** Constructor, static factory, record, lambda/Supplier, or dependency injection.

## Common cross-pattern questions

**Q: Are patterns rules?**  
No. They are names for recurring design tradeoffs. Use the simplest construction mechanism that meets the expected change.

**Q: What is the difference between a constructor and a static factory method?**  
A static factory (for example, `List.of`) has a name, can return subtypes/cached objects, and need not create a new instance. It is an idiom, not the GoF Factory Method pattern unless polymorphic creators decide construction.

**Q: Which patterns favor inheritance vs composition?**  
Classic Factory Method favors inheritance. Abstract Factory, Builder, and Prototype are typically composition-oriented. Singleton is about instance control rather than either relationship.

**Q: How do Spring annotations relate?**  
Spring’s container creates and wires objects, can manage singleton/prototype scopes, and frequently replaces hand-written factories. The conceptual patterns still help explain lifecycle and creation decisions, but an annotation alone does not automatically mean the GoF pattern is present.

**Q: Can patterns be combined?**  
Yes. An Abstract Factory may use Factory Methods; a Builder may be returned by a factory; a prototype registry may itself have one DI-managed instance. Combine only when each pattern solves a distinct problem.

**Q: What signs indicate overengineering?**  
Only one stable implementation, no plausible variation, factory classes that merely wrap `new` without decoupling anything, or more navigation/boilerplate than domain logic.

## Suggested study sequence

1. Run each demo and test.
2. Explain the pattern without looking at this README.
3. Add a `Push` option to Factory Method and Abstract Factory; notice the different change surface.
4. Add a cross-field rule to the Builder (for example, HIGH priority cannot be scheduled more than one hour ahead).
5. Deliberately shallow-copy the Prototype recipient list, run the test, and explain the failure.
6. Replace the enum Singleton with the holder idiom, then discuss which version you prefer and why.

## Final cheat sheet

```text
Need to vary one created type behind a workflow?     Factory Method
Need a compatible set of several created types?      Abstract Factory
Need readable construction with options/invariants?  Builder
Have an existing configured object to copy?          Prototype
Truly require one process-wide instance?              Singleton (consider DI first)
```
