package com.example.creational.factorymethod;

public final class EmailCampaign extends NotificationCampaign {
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
