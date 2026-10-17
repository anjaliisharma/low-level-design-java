package com.solid.ocp.good;

/** Closed for modification: works with ANY Discount, present or future. */
public class DiscountCalculator {
    public double finalPrice(Discount discount, double amount) {
        return amount - discount.apply(amount);
    }
}
