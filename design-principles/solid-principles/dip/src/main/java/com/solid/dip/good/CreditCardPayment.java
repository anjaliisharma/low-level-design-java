package com.solid.dip.good;

public class CreditCardPayment implements PaymentMethod {
    @Override public void pay(double amount) {
        System.out.println("Paid " + amount + " by Credit Card");
    }
}
