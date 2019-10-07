package com.example.demo.test.thread;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore实现的demo
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        productTest();
    }

    /**
     * 简单semaphore例子
     */
    private static void simpleTest(){
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

    /**
     * 自己写的生产-消费模式
     */
    private static void productTest(){
        ProductService productService = new ProductService();
        Runnable chiefRunnable = () -> productService.setFoods();
        Runnable customerRunnable = () -> productService.getFoods();
        //线程数组
        Thread[] chiefThread = new Thread[15];
        Thread[] customerThread = new Thread[15];
        for (int i=0;i<15;i++){
            chiefThread[i] = new Thread(chiefRunnable);
        }
        for (int i=0;i<15;i++){
            customerThread[i] = new Thread(customerRunnable);
        }
        //启动线程
        for (Thread thread : chiefThread){
            thread.start();
        }
        for (Thread thread : customerThread){
            thread.start();
        }
    }
}

/**
 * 简单semaphore例子
 * 开始
 */
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
        //不允许打断的
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
/**
 * 简单semaphore例子
 * 结束
 */


/**
 *生产 消费者 模式
 * 10个厨师生产食物
 * 20个食客消费食物
 * 一次4个食物共享
 */
class ProductService{
    private static int MAX_PLATE = 2;
    private static int MAX_CHIEF = 3;
    private static int MAX_CUSTOMER = 5;
    private Queue<Food> foods;
    private ReentrantLock lock = new ReentrantLock();
    private Condition getCondition = lock.newCondition();
    private Condition setCondition = lock.newCondition();
    private Semaphore cheifSemaphore = new Semaphore(MAX_CHIEF);
    private Semaphore customerSemaphore = new Semaphore(MAX_CUSTOMER);

    ProductService(){
        foods = new ConcurrentLinkedQueue<>();
    }

    /**
     * 消费
     */
    public void getFoods(){
        try {
            customerSemaphore.acquire();
            lock.lock();
            if (foods.isEmpty()){
                //盘子为空，不能取了，取锁锁定，放锁开启
                System.out.println("盘子空了，食客"+ Thread.currentThread() +"等待取菜");
                getCondition.await();
            }

            //盘子不为空了，可以取食物了
            foods.poll();
            System.out.println("食客"+ Thread.currentThread() +"取菜吃了");

            //食客吃完菜了，厨师可以吃菜了
            setCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            customerSemaphore.release();
        }
    }

    /**
     * 生产
     */
    public void setFoods(){
        try {
            cheifSemaphore.acquire();
            lock.lock();
            if (!foods.isEmpty() && foods.size() >= MAX_PLATE){
                //盘子满了，不能放了，放的条件等待
                System.out.println("盘子满了，厨师"+ Thread.currentThread() +"等待做菜");
                setCondition.await();
            }

            //盘子不满了，可以生产了
            foods.add(new Food());
            System.out.println("厨师"+ Thread.currentThread() +"做好菜了");

            //生产了，食客可以吃了，通知取的条件释放
            getCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            cheifSemaphore.release();
        }
    }
}

class Food{}


