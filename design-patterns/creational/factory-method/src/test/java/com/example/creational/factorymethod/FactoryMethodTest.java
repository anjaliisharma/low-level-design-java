package com.example.creational.factorymethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryMethodTest {
    @Test void creatorSubclassSelectsProduct() {
        NotificationCampaign campaign = new SmsCampaign();
        assertEquals("SMS to 9999999999: Hello", campaign.notify("9999999999", "Hello"));
    }
}
