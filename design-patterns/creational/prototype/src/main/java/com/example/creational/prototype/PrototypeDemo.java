package com.example.creational.prototype;

import java.util.List;

public final class PrototypeDemo {
    private PrototypeDemo() {}

    public static void main(String[] args) {
        CampaignRegistry registry = new CampaignRegistry();
        registry.register("interview", new NotificationCampaign(
                "Interview campaign", "Interview update", "Your interview is tomorrow", List.of("candidate@example.com")));

        NotificationCampaign rescheduled = registry.create("interview");
        rescheduled.setSubject("Interview rescheduled");
        rescheduled.addRecipient("recruiter@example.com");
        System.out.println(rescheduled.subject() + " -> " + rescheduled.recipients());
    }
}
