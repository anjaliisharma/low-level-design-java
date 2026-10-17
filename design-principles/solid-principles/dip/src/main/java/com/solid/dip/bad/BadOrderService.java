package com.solid.dip.bad;

/** VIOLATION: high-level OrderService is hard-wired to a concrete payment class. */
public class BadOrderService {
    private final BadCreditCardPayment payment = new BadCreditCardPayment();   // 'new' inside

    public void placeOrder(double amount) {
        System.out.println("Placing order...");
        payment.pay(amount);
    }
}
