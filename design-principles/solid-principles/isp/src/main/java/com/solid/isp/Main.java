package com.solid.isp;

import com.solid.isp.bad.*;
import com.solid.isp.good.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ISP: Interface Segregation Principle ===");

        System.out.println("\n--- BAD: Robot forced to implement eat() and sleep() ---");
        BadWorker robot = new BadRobot();
        robot.work();
        try {
            robot.eat();
        } catch (UnsupportedOperationException e) {
            System.out.println("CRASH: " + e.getMessage());
        }

        System.out.println("\n--- GOOD: small interfaces, each class picks what it needs ---");
        Workable goodRobot = new Robot();
        goodRobot.work();

        Human human = new Human();
        human.work();
        human.eat();
        human.sleep();
    }
}
