package com.example.creational.builder;

import java.time.Instant;

public final class BuilderDemo {
    private BuilderDemo() {}

    public static void main(String[] args) {
        NotificationRequest request = NotificationRequest
                .builder("candidate@example.com", "Your interview is tomorrow")
                .subject("Interview reminder")
                .priority(NotificationRequest.Priority.HIGH)
                .scheduledAt(Instant.now().plusSeconds(3600))
                .addAttachment("interview-guide.pdf")
                .metadata("campaign", "interview-prep")
                .build();
        System.out.println(request.subject() + " -> " + request.recipient());
    }
}
