package com.solid.dip.good;

/** Depends on the PaymentMethod interface; the concrete payment is injected. */
public class OrderService {
    private final PaymentMethod payment;

    public OrderService(PaymentMethod payment) {      // constructor injection
        this.payment = payment;
    }

    public void placeOrder(double amount) {
        System.out.println("Placing order...");
        payment.pay(amount);
    }
}
