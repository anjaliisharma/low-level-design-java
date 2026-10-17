package com.solid.isp.good;

public class Human implements Workable, Eatable, Sleepable {
    @Override public void work()  { System.out.println("Human working"); }
    @Override public void eat()   { System.out.println("Human eating"); }
    @Override public void sleep() { System.out.println("Human sleeping"); }
}
