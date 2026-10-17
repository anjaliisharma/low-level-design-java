package com.example.creational.abstractfactory;

public final class EmailNotificationFactory implements NotificationFactory {
    @Override public MessageTemplate createTemplate() {
        return (recipient, message) -> "Subject: Interview Update\nTo: " + recipient + "\n\n" + message;
    }

    @Override public DeliveryGateway createGateway() {
        return formattedMessage -> "Sent through SMTP -> " + formattedMessage;
    }
}
