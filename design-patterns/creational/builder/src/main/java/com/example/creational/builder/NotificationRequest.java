package com.example.creational.builder;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class NotificationRequest {
    private final String recipient;
    private final String subject;
    private final String body;
    private final Priority priority;
    private final Instant scheduledAt;
    private final List<String> attachments;
    private final Map<String, String> metadata;

    private NotificationRequest(Builder builder) {
        this.recipient = builder.recipient;
        this.subject = builder.subject;
        this.body = builder.body;
        this.priority = builder.priority;
        this.scheduledAt = builder.scheduledAt;
        this.attachments = List.copyOf(builder.attachments);
        this.metadata = Map.copyOf(builder.metadata);
    }

    public static Builder builder(String recipient, String body) { return new Builder(recipient, body); }
    public String recipient() { return recipient; }
    public String subject() { return subject; }
    public String body() { return body; }
    public Priority priority() { return priority; }
    public Instant scheduledAt() { return scheduledAt; }
    public List<String> attachments() { return attachments; }
    public Map<String, String> metadata() { return metadata; }

    public enum Priority { LOW, NORMAL, HIGH }

    public static final class Builder {
        private final String recipient;
        private final String body;
        private String subject = "Notification";
        private Priority priority = Priority.NORMAL;
        private Instant scheduledAt;
        private final java.util.ArrayList<String> attachments = new java.util.ArrayList<>();
        private final java.util.HashMap<String, String> metadata = new java.util.HashMap<>();

        private Builder(String recipient, String body) {
            this.recipient = Objects.requireNonNull(recipient, "recipient");
            this.body = Objects.requireNonNull(body, "body");
        }

        public Builder subject(String subject) { this.subject = Objects.requireNonNull(subject); return this; }
        public Builder priority(Priority priority) { this.priority = Objects.requireNonNull(priority); return this; }
        public Builder scheduledAt(Instant scheduledAt) { this.scheduledAt = scheduledAt; return this; }
        public Builder addAttachment(String attachment) { this.attachments.add(Objects.requireNonNull(attachment)); return this; }
        public Builder metadata(String key, String value) { this.metadata.put(key, value); return this; }

        public NotificationRequest build() {
            if (recipient.isBlank() || body.isBlank()) throw new IllegalStateException("recipient and body must not be blank");
            return new NotificationRequest(this);
        }
    }
}
