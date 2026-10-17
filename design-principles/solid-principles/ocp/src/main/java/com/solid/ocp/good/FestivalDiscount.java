package com.solid.ocp.good;

/** Added LATER. No existing class was modified. */
public class FestivalDiscount implements Discount {
    @Override public double apply(double amount) { return amount * 20 / 100; }
}
