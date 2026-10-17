package com.solid.srp;

import com.solid.srp.bad.BadInvoice;
import com.solid.srp.good.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SRP: Single Responsibility Principle ===");

        System.out.println("\n--- BAD: one class calculates, prints AND saves ---");
        BadInvoice bad = new BadInvoice("Anjali", 500, 2);
        bad.print();
        bad.saveToDatabase();
        System.out.println("Problem: tax change, print change, DB change -> all edit BadInvoice.");

        System.out.println("\n--- GOOD: each class has one job ---");
        Invoice invoice = new Invoice("Anjali", 500, 2);
        double total = new InvoiceCalculator().calculateTotal(invoice);
        new InvoicePrinter().print(invoice, total);
        new InvoiceRepository().save(invoice);
        System.out.println("Benefit: a DB change only touches InvoiceRepository.");
    }
}
