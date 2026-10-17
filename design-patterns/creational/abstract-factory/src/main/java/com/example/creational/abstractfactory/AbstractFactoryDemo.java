package com.example.creational.abstractfactory;

public final class AbstractFactoryDemo {
    private AbstractFactoryDemo() {}

    public static void main(String[] args) {
        NotificationFactory factory = args.length > 0 && args[0].equalsIgnoreCase("sms")
                ? new SmsNotificationFactory()
                : new EmailNotificationFactory();
        System.out.println(new NotificationService(factory)
                .notify("candidate@example.com", "Your interview is tomorrow"));
    }
}
