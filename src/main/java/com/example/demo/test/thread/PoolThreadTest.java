package com.example.demo.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Semaphore创建字符串池（数据库连接池。。。）
 */
public class PoolThreadTest {
    public static void main(String[] args) {

    }
    /**
     * 使用Semaphore创建字符串池（数据库连接池。。。）
     */
    private static void poolTest(){
        ListPool listPool = new ListPool();
        /*
        线程组:12个线程,每个线程都从字符串池中取值，完成后再放回池中
         */
        MyThread[] threadArray = new MyThread[5];
        for (int i = 0; i < threadArray.length; i++){
            threadArray[i] = new MyThread(listPool, String.valueOf(i));
        }
        /*
        运行线程组
         */
        for (int i = 0; i < threadArray.length; i ++){
            threadArray[i].start();
        }
    }
}


/**
 * 字符串池
 * 开始
 */
class ListPool{
    //池中允许存放最大数量
    private int poolMaxSize = 3;
    //初始许可数
    private int semaphorePermits = 5;
    private List<String> list = new ArrayList<String>();
    private Semaphore concurrencySemaphore = new Semaphore(semaphorePermits);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /*
    构造方法：生成列表含3个字符串值
     */
    public ListPool(){
        super();
        for (int i = 0 ; i < poolMaxSize ; i++){
            list.add("爱丽丝" + (i + 1));
        }
    }

    /*
    获取池中字符串值：单处理，5个线程按顺序取池中空闲的字符串（数据库连接池的连接）
     */
    public String get(){
        String getString = null;
        try {
            concurrencySemaphore.acquire();
            lock.lock();
            while (0 == list.size()){
                condition.await();
            }
            getString = list.remove(0);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getString;
    }

    /*
    把字符串放入池中
     */
    public void put(String stringValue){
        lock.lock();
        list.add(stringValue);
        condition.signalAll();
        lock.unlock();
        concurrencySemaphore.release();
    }

}

class MyThread extends Thread{
    private ListPool listPool;
    private String threadName;

    public MyThread(ListPool listPool, String threadName){
        super();
        this.listPool = listPool;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        /*
        无限循环：要用就从池里取空闲的，用完再放回池中
         */
        for (int i = 0 ; i < Integer.MAX_VALUE; i ++ ){
            String getString = listPool.get();
            System.out.println(Thread.currentThread().getName() + ": " + threadName + "线程取得的值为： " + getString + "，时间为： " + System.currentTimeMillis());
            listPool.put(getString);
        }
    }
}
/**
 * 字符串池
 * 结束
 */
