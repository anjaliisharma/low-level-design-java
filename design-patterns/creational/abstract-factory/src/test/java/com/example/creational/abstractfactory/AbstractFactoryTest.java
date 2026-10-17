package com.example.creational.abstractfactory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AbstractFactoryTest {
    @Test void factoryCreatesCompatibleProductFamily() {
        String result = new NotificationService(new EmailNotificationFactory()).notify("a@b.com", "Hello");
        assertTrue(result.contains("SMTP"));
        assertTrue(result.contains("Subject: Interview Update"));
    }
}
