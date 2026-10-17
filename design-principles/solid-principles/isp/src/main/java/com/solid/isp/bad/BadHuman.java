package com.solid.isp.bad;

public class BadHuman implements BadWorker {
    @Override public void work()  { System.out.println("Human working"); }
    @Override public void eat()   { System.out.println("Human eating"); }
    @Override public void sleep() { System.out.println("Human sleeping"); }
}
