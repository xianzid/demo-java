package com.example.demo.test.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        ThreadC threadC = new ThreadC(service);
        threadC.setName("C");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}

class Service{
    private Semaphore semaphore = new Semaphore(1);

    public void testMethod(){
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " begin timer=" + System.currentTimeMillis());

            doSomething();

            System.out.println(Thread.currentThread().getName() + " end timer=" + System.currentTimeMillis());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void test2(){
        semaphore.acquireUninterruptibly();
        System.out.println(Thread.currentThread().getName() + " begin timer=" + System.currentTimeMillis());

        semaphore.release();
    }


    void doSomething() throws InterruptedException {
        Thread.sleep(5000);
    }

}

class ThreadA extends Thread{
    private Service service;
    ThreadA(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.testMethod();
    }
}
class ThreadB extends Thread{
    private Service service;
    ThreadB(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.testMethod();
    }
}
class ThreadC extends Thread{
    private Service service;
    ThreadC(Service service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.testMethod();
    }
}