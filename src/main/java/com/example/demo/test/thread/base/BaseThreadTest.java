package com.example.demo.test.thread.base;

import java.util.concurrent.Callable;

public class BaseThreadTest {
    public static void main(String[] args) {

    }
}

class MyBaseThreadA extends Thread {
    @Override
    public void run() {
        super.run();
    }
}

class MyBaseThreadB implements Runnable{
    @Override
    public void run() {

    }
}

class MyBaseThreadC implements Callable{
    @Override
    public Object call() throws Exception {
        return null;
    }
}