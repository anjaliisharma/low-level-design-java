package com.example.creational.singleton;

public final class SingletonDemo {
    private SingletonDemo() {}

    public static void main(String[] args) {
        NotificationMetrics.INSTANCE.recordSent("email");
        NotificationMetrics.INSTANCE.recordSent("email");
        System.out.println("Emails sent: " + NotificationMetrics.INSTANCE.sentCount("email"));
    }
}
