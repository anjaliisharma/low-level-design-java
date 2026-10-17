package com.example.creational.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class NotificationCampaign {
    private final String name;
    private String subject;
    private String body;
    private final List<String> recipients;

    public NotificationCampaign(String name, String subject, String body, List<String> recipients) {
        this.name = Objects.requireNonNull(name);
        this.subject = Objects.requireNonNull(subject);
        this.body = Objects.requireNonNull(body);
        this.recipients = new ArrayList<>(recipients);
    }

    private NotificationCampaign(NotificationCampaign source) {
        this(source.name, source.subject, source.body, source.recipients);
    }

    public NotificationCampaign copy() { return new NotificationCampaign(this); }
    public String name() { return name; }
    public String subject() { return subject; }
    public String body() { return body; }
    public List<String> recipients() { return List.copyOf(recipients); }
    public void setSubject(String subject) { this.subject = Objects.requireNonNull(subject); }
    public void setBody(String body) { this.body = Objects.requireNonNull(body); }
    public void addRecipient(String recipient) { recipients.add(Objects.requireNonNull(recipient)); }
}
