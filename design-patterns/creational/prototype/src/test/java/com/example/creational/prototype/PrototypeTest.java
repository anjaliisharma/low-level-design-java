package com.example.creational.prototype;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PrototypeTest {
    @Test void copyIsIndependentAndDeepCopiesMutableList() {
        NotificationCampaign original = new NotificationCampaign("Interview", "Update", "Tomorrow", List.of("a@b.com"));
        NotificationCampaign copy = original.copy();
        copy.addRecipient("c@d.com");
        copy.setSubject("Changed");
        assertEquals(List.of("a@b.com"), original.recipients());
        assertEquals("Update", original.subject());
        assertNotSame(original, copy);
    }
}
