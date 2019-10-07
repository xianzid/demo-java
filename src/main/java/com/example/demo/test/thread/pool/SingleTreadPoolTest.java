package com.example.demo.test.thread.pool;

import com.example.demo.tools.TimerUtils;

import java.util.concurrent.*;

public class SingleTreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor(new SingleThreadFactory());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("我运行 " + TimerUtils.getSecond() + " 线程---" + Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println(" 线程---" + Thread.currentThread().getName() + "结束在" + TimerUtils.getSecond());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        //无界，所有线程都同时开始
        service.execute(runnable);
        service.execute(runnable);
        service.execute(runnable);
        service.execute(runnable);
        service.execute(runnable);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(runnable, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
    }
}

class SingleThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("定制池中的线程改名为： " + (int)(Math.random() * 10000));
        return thread;
    }
}
