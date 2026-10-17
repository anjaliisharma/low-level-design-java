package com.solid.srp.good;

/** Job: only hold invoice data. */
public class Invoice {
    private final String customer;
    private final double unitPrice;
    private final int quantity;

    public Invoice(String customer, double unitPrice, int quantity) {
        this.customer = customer;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getCustomer()  { return customer; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity()     { return quantity; }
}
