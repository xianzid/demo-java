package com.example.demo.test.thread.pool;

import com.example.demo.tools.TimerUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        FixedThreadFactory factory = new FixedThreadFactory();
        ExecutorService service = Executors.newFixedThreadPool(2, factory);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Begin------我的线程 " + TimerUtils.getSecond() + " 秒 在" + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println("End--------我的线程 " + TimerUtils.getSecond() + " 秒 在" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        //3个线程同时只能有2个线程在运行，最后一个线程是线程池中复用的线程
        service.execute(runnable);
        service.execute(runnable);
        service.execute(runnable);
    }
}

class FixedThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("定制池中线程改名为： " + ((int)(Math.random() * 10000)));
        return thread;
    }
}
