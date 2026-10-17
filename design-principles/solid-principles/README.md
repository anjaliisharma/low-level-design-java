# SOLID Principles: Interview Revision Notes (Java)

Companion to the `solid-principles` project. Every example below exists in the code:
each principle has a `bad/` package (the violation), a `good/` package (the fix), and a `Main.java` that runs both.

> **How to use this file:** read Section 1 first (2 minutes), then read the principle sections one by one. Finish with Sections 8 to 10 the night before your interview.

---

## Table of Contents
1. [SOLID in 60 seconds](#1-solid-in-60-seconds)
2. [S: Single Responsibility](#2-s--single-responsibility-principle-srp)
3. [O: Open/Closed](#3-o--openclosed-principle-ocp)
4. [L: Liskov Substitution](#4-l--liskov-substitution-principle-lsp)
5. [I: Interface Segregation](#5-i--interface-segregation-principle-isp)
6. [D: Dependency Inversion](#6-d--dependency-inversion-principle-dip)
7. [How the principles connect](#7-how-the-principles-connect)
8. [Interview Q&A](#8-interview-qa)
9. [Real-world (JDK and Spring) examples](#9-real-world-jdk-and-spring-examples)
10. [Cheat sheet and last-minute checklist](#10-cheat-sheet-and-last-minute-checklist)
11. [How to run the project](#11-how-to-run-the-project)

---

## 1. SOLID in 60 seconds

SOLID is five object-oriented design principles that make code **easier to change, test, and understand**.

| Letter | Principle | One-liner | Project example |
|---|---|---|---|
| **S** | Single Responsibility | A class has **one reason to change** | Invoice: calculate / print / save |
| **O** | Open/Closed | **Add** code, don't **edit** code | Customer discounts |
| **L** | Liskov Substitution | A child must be usable **wherever the parent is** | Birds that can't fly |
| **I** | Interface Segregation | Don't force classes to implement **methods they don't use** | Human vs Robot workers |
| **D** | Dependency Inversion | Depend on **interfaces**, not concrete classes | OrderService + payment methods |

**Why it matters:** SOLID code is loosely coupled and highly cohesive. A change in one place doesn't break unrelated places, and each piece can be tested on its own.

---

## 2. S: Single Responsibility Principle (SRP)

### Rule
A class should have **only one reason to change**. Think of it as "one *actor* (team or stakeholder) whose requests can force this class to change," not just "does one thing."

### The violation: `BadInvoice`
```java
public class BadInvoice {
    public double calculateTotal() { ... }   // Finance team's rules
    public void print()            { ... }   // Design team's format
    public void saveToDatabase()   { ... }   // Backend/DBA's storage
}
```

**The pain:** three different requests hit one class:
- Finance: "GST is now 12%." → edit `BadInvoice`
- Design: "New print layout." → edit `BadInvoice`
- DBA: "We're moving to MongoDB." → edit `BadInvoice`

Result: merge conflicts, and a print change could accidentally break tax calculation.

### The fix
```java
Invoice            // holds data only
InvoiceCalculator  // tax/total rules only
InvoicePrinter     // print format only
InvoiceRepository  // storage only
```
A DB migration now touches only `InvoiceRepository`.

### Spot it by
- The word **"and"** in the class description: "calculates *and* prints *and* saves"
- Many unrelated imports (SQL, formatting, business rules) in one file
- One class that is edited for many different reasons

### Say in the interview
> "SRP means a class should have one reason to change. In my invoice example, tax rules, print format, and storage change independently, so each gets its own class. That isolates changes and reduces the risk of breaking unrelated code."

### Watch out
Don't go extreme: a class with one method per file isn't the goal. Group things that change **together for the same reason**.

---

## 3. O: Open/Closed Principle (OCP)

### Rule
Software entities should be **open for extension** (new behavior can be added) but **closed for modification** (you don't edit existing, tested code to add it).

### The violation: `BadDiscountCalculator`
```java
if (customerType.equals("REGULAR"))       return amount * 5 / 100;
else if (customerType.equals("PREMIUM"))  return amount * 10 / 100;
return 0;   // FESTIVAL? Silently returns 0!
```

**The pain:**
- Every new customer type means editing this method and re-testing **all** existing types.
- The failure is **silent**: our demo printed `0.0` for FESTIVAL with no error.

### The fix
```java
interface Discount { double apply(double amount); }

class RegularDiscount  implements Discount { ... }   // 5%
class PremiumDiscount  implements Discount { ... }   // 10%
class FestivalDiscount implements Discount { ... }   // 20%, added LATER

class DiscountCalculator {                            // never changes again
    double finalPrice(Discount d, double amount) {
        return amount - d.apply(amount);
    }
}
```
`FestivalDiscount` is a **new file**. Nothing else was touched.

### Key idea
The **interface is the extension point**. Polymorphism replaces `if/else` on a type or string.

### Spot it by
- Growing `if / else if` or `switch` on a type, string, or enum
- "To add feature X, I must edit method Y" every single time

### Say in the interview
> "OCP means adding features by adding code, not editing code. I replace type-checking if-else chains with an interface and separate implementations, so a new discount type doesn't risk breaking the existing ones."

### Watch out
Don't predict every future change. Apply OCP **after** you see the same code being modified repeatedly (the second or third time).

---

## 4. L: Liskov Substitution Principle (LSP)

### Rule
If your code works with a parent type, it must **keep working when you substitute any child**. A child must honor the parent's promises (behavior, not just method signatures).

### The violation: `BadBird` / `BadPenguin`
```java
class BadBird { void fly() { ... } }                 // promise: ALL birds fly
class BadPenguin extends BadBird {
    @Override void fly() {
        throw new UnsupportedOperationException();   // promise broken
    }
}

void makeItFly(BadBird b) { b.fly(); }               // fine for Sparrow, CRASH for Penguin
```
It **compiles fine** and only fails at runtime. That's what makes LSP violations dangerous.

### The insight
"A penguin **is a** bird" is true in real life, but in code inheritance means "**behaves like** its parent." A penguin doesn't behave like a flying bird.

### The fix
```java
abstract class Bird { void eat() { ... } }           // only what ALL birds do
interface Flyable   { void fly(); }                  // only flyers opt in

class Sparrow extends Bird implements Flyable { ... }
class Eagle   extends Bird implements Flyable { ... }
class Penguin extends Bird { ... }                   // no fly() to break
```

### Spot it by
- An override that **throws** `UnsupportedOperationException` or **does nothing**
- `instanceof` checks before calling a method on a parent type
- A child that needs to **weaken** what the parent promises (stricter preconditions, weaker postconditions)

### Classic bonus example: Square extends Rectangle
`Rectangle` lets you set width and height independently. `Square` must keep them equal, so `setWidth(5)` also changes height. Code that expects a rectangle (`area == width * height` after setting each independently) breaks. **Fix:** don't make Square a subtype of a mutable Rectangle.

### Say in the interview
> "LSP says a subclass must be usable anywhere its parent is, without breaking correctness. If an override throws UnsupportedOperationException, like Penguin.fly(), the hierarchy is wrong. I fix it by moving the optional behavior into a separate interface like Flyable."

---

## 5. I: Interface Segregation Principle (ISP)

### Rule
No class should be **forced to depend on methods it doesn't use**. Prefer many small, focused interfaces over one large one.

### The violation: `BadWorker` / `BadRobot`
```java
interface BadWorker { void work(); void eat(); void sleep(); }

class BadRobot implements BadWorker {
    public void work()  { ... }
    public void eat()   { throw new UnsupportedOperationException(); }
    public void sleep() { throw new UnsupportedOperationException(); }
}
```
The robot signs a contract it can't fulfil, and anyone holding a `BadWorker` can call `eat()` and crash.

### The fix
```java
interface Workable  { void work(); }
interface Eatable   { void eat(); }
interface Sleepable { void sleep(); }

class Human implements Workable, Eatable, Sleepable { ... }
class Robot implements Workable { ... }              // only what it truly does
```
Code that needs a worker asks for a `Workable`, and both Human and Robot fit.

### ISP vs LSP (a favorite follow-up)
| | Problem lives in... | Example |
|---|---|---|
| **LSP** | The **subclass** breaks the **parent's** behavior (inheritance issue) | `BadPenguin.fly()` throws |
| **ISP** | The **interface** is too big and forces fake methods (interface design issue) | `BadRobot.eat()` throws |

Both can show up as `UnsupportedOperationException`. The **fix location** differs: LSP → fix the hierarchy, ISP → split the interface.

### Spot it by
- Empty method bodies or methods that just throw
- Implementers that use only a subset of an interface's methods

### Say in the interview
> "ISP says don't force a class to depend on methods it doesn't use. Instead of one fat Worker interface, I split it into Workable, Eatable, and Sleepable, so Robot implements only Workable and there are no fake methods."

---

## 6. D: Dependency Inversion Principle (DIP)

### Rule
1. **High-level modules** (business logic) should not depend on **low-level modules** (details). **Both** should depend on **abstractions**.
2. Abstractions should not depend on details. Details should depend on abstractions.

### The violation: `BadOrderService`
```java
class BadOrderService {
    private final BadCreditCardPayment payment = new BadCreditCardPayment();  // 'new' inside!
    void placeOrder(double amount) { payment.pay(amount); }
}
```

**The pain:**
1. **Inflexible:** to support UPI you must edit `BadOrderService`.
2. **Untestable:** every test hits the real payment code. You can't unit test without charging a card.

### The fix
```java
interface PaymentMethod { void pay(double amount); }

class CreditCardPayment implements PaymentMethod { ... }
class UpiPayment        implements PaymentMethod { ... }

class OrderService {
    private final PaymentMethod payment;
    OrderService(PaymentMethod payment) { this.payment = payment; }   // injected
    void placeOrder(double amount) { payment.pay(amount); }
}
```

The caller decides:
```java
new OrderService(new UpiPayment()).placeOrder(999);
```

Testing is trivial: a **one-line fake**:
```java
PaymentMethod fake = captured::add;      // no real payment happens
new OrderService(fake).placeOrder(999);
```

### Why "inversion"?
Normally high-level code depends *down* on details. Here **both** depend on the interface, and the details must conform to what the business logic needs. The dependency direction is flipped.

### DIP vs Dependency Injection vs IoC vs Spring
| Term | What it is |
|---|---|
| **DIP** | The **principle**: depend on abstractions |
| **DI** (Dependency Injection) | A **technique**: pass dependencies in from outside (constructor, setter, field) |
| **IoC** (Inversion of Control) | A **broader idea**: a framework or container controls object creation and wiring |
| **Spring `@Autowired`** | A **framework feature** that automates DI |

### Spot it by
- `new ConcreteClass()` inside business logic
- You can't unit test without a real database, network, or gateway

### Say in the interview
> "DIP says high-level modules should depend on abstractions, not concrete classes. My OrderService depends on a PaymentMethod interface and receives it through the constructor, so I can switch between credit card and UPI without touching it, and inject a fake in unit tests."

---

## 7. How the principles connect

- **DIP creates the interface** that **OCP** then uses to add behavior (`Discount`, `PaymentMethod`).
- **LSP keeps OCP safe:** if new subclasses break the parent's contract, "extending" becomes dangerous.
- **ISP keeps interfaces small**, which makes **LSP** easier to honor (fewer promises → fewer broken ones).
- **SRP keeps classes small** enough that all of the above stay manageable.

```
SRP  →  small focused classes
ISP  →  small focused interfaces
DIP  →  depend on those interfaces
OCP  →  extend by adding new implementations
LSP  →  guarantees those implementations are safe to swap
```

---

## 8. Interview Q&A

**Q1. What is SOLID?**
Five OOP design principles (SRP, OCP, LSP, ISP, DIP) that make code maintainable, extensible, and testable by reducing coupling and increasing cohesion.

**Q2. Which principle do you use most in real projects?**
DIP. In Spring Boot I inject interfaces into services (constructor injection), which keeps them loosely coupled and easy to unit test with mocks.

**Q3. Difference between DIP and Dependency Injection?**
DIP is the design **principle** (depend on abstractions). DI is a **technique** to achieve it (supply dependencies from outside). Spring automates DI.

**Q4. How are OCP and DIP related?**
DIP provides the abstraction (interface). OCP uses that abstraction to add new behavior without modifying existing code.

**Q5. Difference between ISP and SRP?**
SRP is about a **class** having one reason to change. ISP is about an **interface** not forcing clients to depend on methods they don't use. Both are about keeping things focused, but they apply to different constructs.

**Q6. Give an example of an LSP violation.**
`Penguin extends Bird` where `Bird.fly()` exists and Penguin throws in `fly()`. Or `Square extends Rectangle`. Fix by restructuring the hierarchy (a `Flyable` interface).

**Q7. How does LSP relate to method overriding rules?**
A subclass override should not **strengthen preconditions** (demand more from callers), **weaken postconditions** (deliver less), or throw **new unexpected exceptions**. Java's checked-exception rules enforce part of this.

**Q8. Which principle does the Strategy pattern demonstrate?**
OCP (add new strategies without changing the context) and DIP (context depends on a strategy interface). Our `Discount` and `PaymentMethod` designs are Strategy pattern examples.

**Q9. Can SOLID be overdone?**
Yes. Splitting everything into interfaces and tiny classes adds indirection and complexity. Apply the principles where change is **likely** or testing is **painful**, not everywhere. YAGNI still applies: an interface with one implementation that will never change is often unnecessary.

**Q10. How do you know a class violates SRP?**
Ask "how many different reasons or stakeholders could force me to edit this?" If more than one, split it. Also look for "and" in its description.

**Q11. Does OCP mean you can never modify a class?**
No. You'll still fix bugs and refactor. OCP means that adding **new behavior** shouldn't require editing **existing working logic**.

**Q12. How does SOLID help testing?**
DIP lets you inject fakes or mocks. SRP gives small units that are easy to test. OCP means new features don't force re-testing old ones.

**Q13. Why is `new` inside a service a problem?**
It hard-wires a concrete dependency. You can't swap the implementation or substitute a test double, which breaks DIP.

**Q14. What is the "silent failure" risk in OCP violations?**
With if-else on a string type, an unhandled value falls through to a default (like our `0.0` discount) instead of failing loudly. Polymorphism forces every type to define its behavior.

**Q15. Are there SOLID violations inside the JDK itself?**
Yes. `Collections.unmodifiableList(...)` and `Arrays.asList(...)` return `List`s whose `add()` throws `UnsupportedOperationException`, a well-known LSP compromise. Good to mention if asked for a "real" example.

---

## 9. Real-world (JDK and Spring) examples

| Principle | Where you see it |
|---|---|
| **SRP** | Spring layers: `@Controller` (web), `@Service` (business), `@Repository` (storage) |
| **OCP** | `Comparator`: add new sort orders without changing `Collections.sort()`. Also `Collections.sort(list, comparator)` |
| **LSP** | Using `List<String> list = new ArrayList<>()` or `new LinkedList<>()` interchangeably. (Counter-example: unmodifiable lists throwing on `add`) |
| **ISP** | `Runnable`, `Callable`, `Comparable`: tiny single-method interfaces. Java also splits `Readable`/`Closeable` etc. |
| **DIP** | `List<String> list = new ArrayList<>()`, Spring `@Autowired` constructor injection, JDBC's `Connection` / `DataSource` interfaces |

---

## 10. Cheat sheet and last-minute checklist

### Cheat sheet

| Principle | One-liner | Red flag in code | Project fix |
|---|---|---|---|
| **SRP** | One reason to change | "Calculates AND prints AND saves" | Split into 4 classes |
| **OCP** | Add code, don't edit code | Growing if-else on type | `Discount` interface |
| **LSP** | Child can replace parent | Override that throws / does nothing | `Flyable` interface |
| **ISP** | Small interfaces | Empty or throwing methods | `Workable` / `Eatable` / `Sleepable` |
| **DIP** | Depend on interfaces | `new` inside business logic | Inject `PaymentMethod` |

### The 4-step answer formula (use for ANY "explain principle X" question)
1. **Define** it in one sentence.
2. **Show the bad code** in 2 to 3 lines.
3. **Explain the pain** with a change request ("then the business wants UPI...").
4. **Show the fix** and name the benefit.

> Step 3 is the one most candidates skip, and it's what separates memorized definitions from real understanding.

### 2-minute "explain all five" script
> "SOLID is five design principles for maintainable code. **SRP**: a class has one reason to change, so I split an invoice into calculator, printer, and repository. **OCP**: add features by adding classes, not editing code, like new discount types via an interface. **LSP**: subclasses must be substitutable for their parent, so a Penguin shouldn't inherit `fly()`. **ISP**: prefer small interfaces, so a Robot doesn't implement `eat()`. **DIP**: depend on abstractions and inject them, like injecting a `PaymentMethod` into `OrderService`, which also makes testing easy."

### Night-before checklist
- [ ] I can define all five in one sentence each
- [ ] For each, I can name the **bad code**, the **pain**, and the **fix** from the project
- [ ] I can explain **DIP vs DI vs IoC**
- [ ] I can explain **ISP vs LSP** and **ISP vs SRP**
- [ ] I can give a JDK or Spring example for each principle
- [ ] I know when SOLID is **overkill**
- [ ] I've run the project once and read each `Main.java` output

---

## 11. How to run the project

```
solid-principles/
├── srp/  ocp/  lsp/  isp/  dip/        <- one module per principle
│   └── src/main/java/com/solid/<x>/
│       ├── bad/        (violation)
│       ├── good/       (fix)
│       └── Main.java   (runs both)
├── run-all.bat / run-all.sh
└── pom.xml
```

**Windows** (needs a JDK 17+ with `javac` on PATH):
```
run-all.bat           (all five)
run-all.bat srp       (one module)
```

**Mac/Linux:**
```
./run-all.sh
./run-all.sh dip
```

**Maven (optional):**
```
mvn clean compile
mvn -q exec:java -pl srp
```

**IDE:** open the root folder as a Maven project, then right-click any `Main.java` and Run.

### Live-coding tip
If an interviewer asks you to demo, run one module (DIP or OCP are the most impressive), point at the bad output, then show the good version and explain what changed. Keep the explanation in the 4-step formula from Section 10.

Good luck with the interview! 🚀
