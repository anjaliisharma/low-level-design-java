package com.solid.srp.good;

/** Job: presentation. Changes only when the print format changes. */
public class InvoicePrinter {
    public void print(Invoice invoice, double total) {
        System.out.println("Invoice for " + invoice.getCustomer() + " | Total: " + total);
    }
}
