package com.solid.srp.good;

/** Job: persistence. Changes only when the storage changes. */
public class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Saving invoice of " + invoice.getCustomer() + " to database...");
    }
}
