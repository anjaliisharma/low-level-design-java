package com.example.creational.singleton;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    @AfterEach void cleanUp() { NotificationMetrics.INSTANCE.reset(); }

    @Test void enumExposesOneInstanceAndThreadSafeCounter() {
        IntStream.range(0, 1_000).parallel().forEach(i -> NotificationMetrics.INSTANCE.recordSent("email"));
        assertSame(NotificationMetrics.INSTANCE, NotificationMetrics.valueOf("INSTANCE"));
        assertEquals(1_000, NotificationMetrics.INSTANCE.sentCount("email"));
    }
}
