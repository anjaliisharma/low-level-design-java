package com.example.creational.abstractfactory;

public final class SmsNotificationFactory implements NotificationFactory {
    @Override public MessageTemplate createTemplate() {
        return (recipient, message) -> "To: " + recipient + " | " + message.substring(0, Math.min(message.length(), 160));
    }

    @Override public DeliveryGateway createGateway() {
        return formattedMessage -> "Sent through SMS gateway -> " + formattedMessage;
    }
}
