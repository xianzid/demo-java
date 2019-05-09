package com.example.demo.test.thread.pool;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }
}
