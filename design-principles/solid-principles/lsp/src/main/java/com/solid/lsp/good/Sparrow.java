package com.solid.lsp.good;

public class Sparrow extends Bird implements Flyable {
    @Override public void fly() { System.out.println("Sparrow is flying"); }
}
