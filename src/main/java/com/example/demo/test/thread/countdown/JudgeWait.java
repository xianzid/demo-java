package com.example.demo.test.thread.countdown;

import java.util.concurrent.CountDownLatch;

public class JudgeWait {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch maxRunner = new CountDownLatch(10);
        SporterThread[] tArray = new SporterThread[Integer.parseInt("" + maxRunner.getCount())];
        for (SporterThread sporter : tArray){
            sporter = new SporterThread(maxRunner);
            sporter.setName("线程"+(sporter.getId()));
            sporter.start();
        }
        //countDownLatch.count初始为10--等待，当count数值减到0时，再执行下面的操作
        maxRunner.await();
        System.out.println("都回来了！");
    }
}

/**
 * 运动员线程
 */
class SporterThread extends Thread{
    private CountDownLatch maxRunner;

    SporterThread(CountDownLatch maxRunner){
        super();
        this.maxRunner = maxRunner;
    }

    @Override
    public void run(){
        try {
            System.out.println(Thread.currentThread().getName() + "运动员出发");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "运动员跑到了");
            //运动员跑到了，等待数（初始为10）-1（等待释放1个）
            maxRunner.countDown();
            System.out.println(Thread.currentThread().getName() + "运动员等待-1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


