package com.example.demo.test.thread.base;

public class ThreadDataShareTest {
    public static void main(String[] args) {
        testRunnable();
    }

    /**
     * 每个线程维护自己的10张票，没有数据共享的概念
     */
    private static void testThread(){
        Thread thread1 = new BaseDataThreadA();
        Thread thread2 = new BaseDataThreadA();
        Thread thread3 = new BaseDataThreadA();
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 多线程一起消费这10张票
     */
    private static void testRunnable(){
        final BaseDataThreadB baseDataThreadB = new BaseDataThreadB();
        Thread thread1 = new Thread(baseDataThreadB);
        Thread thread2 = new Thread(baseDataThreadB);
        Thread thread3 = new Thread(baseDataThreadB);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class BaseDataThreadA extends Thread{
    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (this.ticket > 0){
                System.out.println("票数为" + this.ticket--);
            }
        }
    }
}

class BaseDataThreadB implements Runnable{
    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (this.ticket > 0){
                System.out.println("票数为：" + this.ticket--);
            }
        }
    }
}