package com.example.creational.abstractfactory;

import java.util.Objects;

public final class NotificationService {
    private final MessageTemplate template;
    private final DeliveryGateway gateway;

    public NotificationService(NotificationFactory factory) {
        Objects.requireNonNull(factory);
        this.template = factory.createTemplate();
        this.gateway = factory.createGateway();
    }

    public String notify(String recipient, String message) {
        return gateway.deliver(template.format(recipient, message));
    }
}
