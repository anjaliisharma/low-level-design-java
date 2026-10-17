package com.example.heap;

public interface Heap<T> {
    void add(T val);
    T pop();
    T peek();
    boolean isEmpty();
}
