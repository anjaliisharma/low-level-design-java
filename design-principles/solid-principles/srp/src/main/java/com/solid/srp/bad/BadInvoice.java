package com.solid.srp.bad;

/**
 * VIOLATION: this class has 3 reasons to change:
 *   1. tax/total rules change      -> calculateTotal()
 *   2. print format changes        -> print()
 *   3. database changes            -> saveToDatabase()
 */
public class BadInvoice {
    private final String customer;
    private final double unitPrice;
    private final int quantity;

    public BadInvoice(String customer, double unitPrice, int quantity) {
        this.customer = customer;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public double calculateTotal() {                 // business logic
        double subtotal = unitPrice * quantity;
        return subtotal + subtotal * 18 / 100;       // 18% GST
    }

    public void print() {                            // presentation logic
        System.out.println("Invoice for " + customer + " | Total: " + calculateTotal());
    }

    public void saveToDatabase() {                   // persistence logic
        System.out.println("Saving invoice of " + customer + " to database...");
    }
}
