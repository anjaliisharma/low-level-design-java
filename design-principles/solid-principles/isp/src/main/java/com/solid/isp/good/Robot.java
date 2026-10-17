package com.solid.isp.good;

/** Only implements what a robot really does. */
public class Robot implements Workable {
    @Override public void work() { System.out.println("Robot working"); }
}
