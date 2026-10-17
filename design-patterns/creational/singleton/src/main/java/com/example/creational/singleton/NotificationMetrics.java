package com.example.creational.singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public enum NotificationMetrics {
    INSTANCE;

    private final ConcurrentHashMap<String, LongAdder> sentByChannel = new ConcurrentHashMap<>();

    public void recordSent(String channel) {
        sentByChannel.computeIfAbsent(channel.toUpperCase(), ignored -> new LongAdder()).increment();
    }

    public long sentCount(String channel) {
        LongAdder counter = sentByChannel.get(channel.toUpperCase());
        return counter == null ? 0 : counter.sum();
    }

    public void reset() { sentByChannel.clear(); }
}
