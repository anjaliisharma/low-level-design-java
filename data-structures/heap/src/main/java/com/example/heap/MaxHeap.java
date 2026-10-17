package com.example.heap;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> implements Heap<T> {
    private ArrayList<T> list;

    public MaxHeap() {
        list = new ArrayList<>();
        list.add(null);
    }

    private void swap(int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    private int parent(int index) {
        return index / 2;
    }

    private int left(int index) {
        return 2 * index;
    }

    private int right(int index) {
        return 2 * index + 1;
    }

    private void upHeap(int index) {
        if (index == 1) return;

        int p = parent(index);
        if (list.get(index).compareTo(list.get(p)) > 0) {
            swap(index, p);
            upHeap(p);
        }
    }

    private void downHeap(int index) {
        int max = index;
        int left = left(index);
        int right = right(index);

        if (left < list.size()
                && list.get(max).compareTo(list.get(left)) < 0) {
            max = left;
        }

        if (right < list.size()
                && list.get(max).compareTo(list.get(right)) < 0) {
            max = right;
        }

        if (index == max) return;

        swap(index, max);
        downHeap(max);
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 1;
    }

    @Override
    public void add(T val) {
        list.add(val);
        upHeap(list.size() - 1);
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;

        T removed = list.get(1);
        swap(1, list.size() - 1);
        list.remove(list.size() - 1);

        if (!isEmpty()) downHeap(1);
        return removed;
    }

    @Override
    public T peek() {
        if (isEmpty()) return null;
        return list.get(1);
    }
}
