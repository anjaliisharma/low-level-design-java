package com.solid.ocp.bad;

/** VIOLATION: every new customer type forces us to EDIT this method. */
public class BadDiscountCalculator {
    public double getDiscount(String customerType, double amount) {
        if (customerType.equals("REGULAR")) {
            return amount * 5 / 100;
        } else if (customerType.equals("PREMIUM")) {
            return amount * 10 / 100;
        }
        // Want a FESTIVAL discount? Edit here, then re-test everything.
        return 0;
    }
}
