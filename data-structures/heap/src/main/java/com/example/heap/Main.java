package com.example.heap;

public class Main {
    public static void main(String[] args) {
        Heap<Integer> heap = new MaxHeap<>();
        heap.add(1);
        heap.add(3);
        heap.add(0);

        while (!heap.isEmpty()) {
            System.out.print(heap.pop() + " ");
        }

        System.out.println();

        Heap<String> heap1 = new MinHeap<>();
        heap1.add("Parth");
        heap1.add("Shubh");

        while (!heap1.isEmpty()) {
            System.out.print(heap1.pop() + " ");
        }
    }
}
