# Heap From Scratch in Java

An array-backed binary heap following the simple design in Shubh Patel's heap
tutorial: one-based indexing (`null` at index 0), recursive `upHeap` and
`downHeap`, and no `PriorityQueue`.

## Project structure

```text
src/main/java/com/example/heap/
├── Heap.java
├── MinHeap.java
├── MaxHeap.java
└── Main.java
```

## Run

From the project directory:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.example.heap.Main"
```

Alternatively, open the folder as a Maven project in IntelliJ IDEA or Eclipse and
run `Main.java`.

## Complexity

| Operation | Complexity |
|---|---:|
| `peek()` | O(1) |
| `add()` | O(log n) |
| `pop()` | O(log n) |
| `isEmpty()` | O(1) |
