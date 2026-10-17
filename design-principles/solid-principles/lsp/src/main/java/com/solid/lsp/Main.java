package com.solid.lsp;

import com.solid.lsp.bad.*;
import com.solid.lsp.good.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== LSP: Liskov Substitution Principle ===");

        System.out.println("\n--- BAD: substituting Penguin for Bird crashes ---");
        List<BadBird> badBirds = List.of(new BadSparrow(), new BadPenguin());
        for (BadBird bird : badBirds) {
            try {
                bird.fly();
            } catch (UnsupportedOperationException e) {
                System.out.println("CRASH: " + e.getMessage());
            }
        }

        System.out.println("\n--- GOOD: every Bird can eat; only Flyable birds fly ---");
        List<Bird> birds = List.of(new Sparrow(), new Eagle(), new Penguin());
        for (Bird bird : birds) {
            bird.eat();                              // safe for ALL birds
        }
        List<Flyable> flyers = List.of(new Sparrow(), new Eagle());
        for (Flyable f : flyers) {
            f.fly();                                 // safe: only flyers here
        }
    }
}
