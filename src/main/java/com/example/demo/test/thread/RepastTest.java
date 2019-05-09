package com.example.demo.test.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Semaphore实现多生产者/多消费者模式
 */
public class RepastTest {
    private static int COSUMER_SIZE = 20;
    public static void main(String[] args) throws InterruptedException {
        RepastService service = new RepastService();
        ThreadProducters[] chefs = new ThreadProducters[COSUMER_SIZE];
        ThreadConsumers[] consumers = new ThreadConsumers[COSUMER_SIZE];
        for (int i = 0 ; i < COSUMER_SIZE ; i ++){
            chefs[i] = new ThreadProducters(service);
            consumers[i] = new ThreadConsumers(service);
        }
        Thread.sleep(2000);
        for (ThreadProducters chef : chefs){
            chef.start();
        }
        for (ThreadConsumers consumer : consumers){
            consumer.start();
        }
    }
}

/**
 *处理类
 */
class RepastService{
    //厨师：同时有5个厨师一起做菜
    volatile private Semaphore setSemaphore = new Semaphore(5);
    //就餐者：同时有10个消费者可以一起取菜
    volatile private Semaphore getSemaphore = new Semaphore(10);
    volatile private ReentrantLock lock = new ReentrantLock();
    //放菜锁
    volatile private Condition setCondition = lock.newCondition();
    //取菜锁
    volatile private Condition getCondition = lock.newCondition();
    //最多只有4个盒子存放菜品
    volatile private Object[] productPosition = new Object[4];

    /*
    就餐者需要判断：盒子是否全是空的
     */
    public boolean isEmpty(){
        boolean isEmpty = true;
        for (Object produce : productPosition){
            if (null != produce){
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    /*
    厨师需要判断：是否全有菜，没空盒子了
     */
    public boolean isFull(){
        boolean isFull = true;
        for (Object product : productPosition){
            if (null == product){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    /*
    厨师往盒子里放菜
     */
    public void set(){
        try {
            System.out.println("厨师准备往盒子里放菜。。。");
            setSemaphore.acquire();
            lock.lock();

            //1.判断，是否盒子全满了，没盒子装菜了
            while (isFull()){
                //2。全满了，放等待/阻塞直到有空盒子
                System.out.println("盒子全满了，生产者在等待。。。");
                setCondition.await();
            }

            //3。阻塞直到有空盒子了，把菜放入盒子
            for (int i = 0 ; i < productPosition.length ; i ++){
                if (null == productPosition[i]){
                    productPosition[i] = "数据";
                    System.out.println(Thread.currentThread().getName() + "生产了" + productPosition[i]);
                    break;
                }
            }

            //4。有菜了，就餐者可取了，取解锁
            getCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            setSemaphore.release();
        }
    }

    /*
    就餐者从盒子里取菜
     */
    public void get(){
        try {
            System.out.println("就餐者准备取菜。。。");
            getSemaphore.acquire();
            lock.lock();

            //1.判断是否有菜，盒子是否为全空
            while (isEmpty()){
                System.out.println("消费者在等待。。。");
                //2。全空的话，取等待，阻塞直到有菜
                getCondition.await();
            }

            //3。有菜了，从非空的盒子里取菜
            for (int i = 0 ; i < productPosition.length ; i ++){
                if (null != productPosition[i]){
                    System.out.println(Thread.currentThread().getName() + " 消费了" + productPosition[i]);
                    productPosition[i] = null;
                    break;
                }
            }

            //4。有空盒子了，放菜解锁
            setCondition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getSemaphore.release();
        }

    }
}

/*
生产者线程操作类
 */
class ThreadProducters extends Thread{
    RepastService service;
    ThreadProducters(RepastService service){
        super();
        this.service = service;
    }
    @Override
    public void run() {
        service.set();
    }
}

class ThreadConsumers extends Thread{
    RepastService service;
    ThreadConsumers(RepastService service){
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.get();
    }
}
