package com.solid.lsp.good;

public class Eagle extends Bird implements Flyable {
    @Override public void fly() { System.out.println("Eagle is soaring"); }
}
