package com.solid.lsp.bad;

/** Promises that EVERY bird can fly, which is not true. */
public class BadBird {
    public void fly() {

        System.out.println(getClass().getSimpleName() + " is flying");
    }
}
