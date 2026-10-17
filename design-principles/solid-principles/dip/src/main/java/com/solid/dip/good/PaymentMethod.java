package com.solid.dip.good;

/** The abstraction that both high-level and low-level code depend on. */
public interface PaymentMethod {
    void pay(double amount);
}
