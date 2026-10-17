package com.solid.srp.good;

/** Job: total/tax rules. Changes only when pricing rules change. */
public class InvoiceCalculator {
    public double calculateTotal(Invoice invoice) {
        double subtotal = invoice.getUnitPrice() * invoice.getQuantity();
        return subtotal + subtotal * 18 / 100;       // 18% GST
    }
}
