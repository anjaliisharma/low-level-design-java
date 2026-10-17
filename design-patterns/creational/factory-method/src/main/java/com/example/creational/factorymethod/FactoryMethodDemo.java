package com.example.creational.factorymethod;

public final class FactoryMethodDemo {
    private FactoryMethodDemo() {}

    public static void main(String[] args) {
        NotificationCampaign campaign = args.length > 0 && args[0].equalsIgnoreCase("sms")
                ? new SmsCampaign()
                : new EmailCampaign();
        System.out.println(campaign.notify("candidate@example.com", "Your interview is tomorrow"));
    }
}
