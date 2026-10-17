package com.solid.ocp.good;

public class RegularDiscount implements Discount {
    @Override public double apply(double amount) { return amount * 5 / 100; }
}
