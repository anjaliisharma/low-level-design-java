package com.example.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public final class CampaignRegistry {
    private final Map<String, NotificationCampaign> prototypes = new HashMap<>();

    public void register(String key, NotificationCampaign prototype) {
        prototypes.put(key, prototype.copy());
    }

    public NotificationCampaign create(String key) {
        NotificationCampaign prototype = prototypes.get(key);
        if (prototype == null) throw new IllegalArgumentException("Unknown campaign: " + key);
        return prototype.copy();
    }
}
