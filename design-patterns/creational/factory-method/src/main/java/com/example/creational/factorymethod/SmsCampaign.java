package com.example.creational.factorymethod;

public final class SmsCampaign extends NotificationCampaign {
    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}
