package com.example.creational.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {
    @Test void suppliesDefaultsAndBuildsImmutableCollections() {
        NotificationRequest request = NotificationRequest.builder("a@b.com", "Hello").addAttachment("guide.pdf").build();
        assertEquals(NotificationRequest.Priority.NORMAL, request.priority());
        assertThrows(UnsupportedOperationException.class, () -> request.attachments().add("other.pdf"));
    }
}
