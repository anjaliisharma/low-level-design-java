package com.solid.isp.bad;

/** VIOLATION: a "fat" interface that forces every worker to do everything. */
public interface BadWorker {
    void work();
    void eat();
    void sleep();
}
