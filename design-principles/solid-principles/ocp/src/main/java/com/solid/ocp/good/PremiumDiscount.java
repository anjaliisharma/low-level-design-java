package com.solid.ocp.good;

public class PremiumDiscount implements Discount {
    @Override public double apply(double amount) { return amount * 10 / 100; }
}
