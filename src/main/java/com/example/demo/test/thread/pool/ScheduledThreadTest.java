package com.example.demo.test.thread.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadTest {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
        scheduledThreadPoolExecutor.schedule(new MyCallable(), 0, TimeUnit.SECONDS);

    }

}

class MyCallable implements Callable{

    @Override
    public Object call() throws Exception {
        return null;
    }
}