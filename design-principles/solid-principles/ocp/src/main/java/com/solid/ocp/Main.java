package com.solid.ocp;

import com.solid.ocp.bad.BadDiscountCalculator;
import com.solid.ocp.good.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== OCP: Open/Closed Principle ===");
        double amount = 1000;

        System.out.println("\n--- BAD: if-else chain ---");
        BadDiscountCalculator bad = new BadDiscountCalculator();
        System.out.println("REGULAR  discount: " + bad.getDiscount("REGULAR", amount));
        System.out.println("PREMIUM  discount: " + bad.getDiscount("PREMIUM", amount));
        System.out.println("FESTIVAL discount: " + bad.getDiscount("FESTIVAL", amount)
                + "   <-- silently 0, we must edit the class");

        System.out.println("\n--- GOOD: extend by adding classes ---");
        DiscountCalculator calc = new DiscountCalculator();
        System.out.println("Regular  final price: " + calc.finalPrice(new RegularDiscount(), amount));
        System.out.println("Premium  final price: " + calc.finalPrice(new PremiumDiscount(), amount));
        System.out.println("Festival final price: " + calc.finalPrice(new FestivalDiscount(), amount)
                + "   <-- new class, zero edits to existing code");
    }
}
