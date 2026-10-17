package com.example.creational.factorymethod;

public abstract class NotificationCampaign {
    public final String notify(String recipient, String message) {
        Notification notification = createNotification();
        return notification.send(recipient, message);
    }

    protected abstract Notification createNotification();
}
