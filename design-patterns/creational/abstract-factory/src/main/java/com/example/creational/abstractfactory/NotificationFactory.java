package com.example.creational.abstractfactory;

public interface NotificationFactory {
    MessageTemplate createTemplate();
    DeliveryGateway createGateway();
}
