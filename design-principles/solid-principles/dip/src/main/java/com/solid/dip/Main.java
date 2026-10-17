package com.solid.dip;

import com.solid.dip.bad.BadOrderService;
import com.solid.dip.good.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== DIP: Dependency Inversion Principle ===");

        System.out.println("\n--- BAD: OrderService can ONLY use Credit Card ---");
        new BadOrderService().placeOrder(999);
        System.out.println("Problem: to support UPI we must edit BadOrderService.");

        System.out.println("\n--- GOOD: payment method is injected ---");
        new OrderService(new CreditCardPayment()).placeOrder(999);
        new OrderService(new UpiPayment()).placeOrder(999);

        System.out.println("\n--- BONUS: easy testing with a fake payment ---");
        List<Double> captured = new ArrayList<>();
        PaymentMethod fakePayment = captured::add;    // no real payment happens
        new OrderService(fakePayment).placeOrder(999);
        System.out.println("Fake payment captured: " + captured);
    }
}
