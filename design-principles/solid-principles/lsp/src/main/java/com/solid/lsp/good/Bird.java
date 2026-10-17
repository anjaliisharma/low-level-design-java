package com.solid.lsp.good;

/** Base type only promises what EVERY bird can really do. */
public abstract class Bird {
    public void eat() {
        System.out.println(getClass().getSimpleName() + " is eating");
    }
}
