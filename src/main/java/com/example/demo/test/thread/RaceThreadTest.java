package com.example.demo.test.thread;

//import com.example.demo.tools.TimerUtils;
import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch来构造运动员到达
 * 构造初始有个count值，count=0 时await()继续
 * countDown减，能继续
 * await等待count减完
 */
public class RaceThreadTest {
    //有10个运动员
    private static final int SPORTS_MAN_COUNT = 10;
    //等待10个运动员
    private static CountDownLatch comeTag = new CountDownLatch(SPORTS_MAN_COUNT);
    private static CountDownLatch waitTag = new CountDownLatch(1);
    private static CountDownLatch beginTag = new CountDownLatch(1);
    private static  CountDownLatch endTag = new CountDownLatch(SPORTS_MAN_COUNT);

    public static void main(String[] args) {
        try {
//            String beginSecond = TimerUtils.getSecond();
            ThreadSportsMan[] sportsMans = new ThreadSportsMan[SPORTS_MAN_COUNT];
            for (int i = 0 ; i < sportsMans.length ; i++){
                sportsMans[i] = new ThreadSportsMan(comeTag, waitTag, beginTag, endTag);
                sportsMans[i].start();
            }
            System.out.println("裁判员等待运动员到达起跑点。。。"/* + "开始时间为：" + beginSecond*/);
            comeTag.await();
            //10个运动员都到达了
            Thread.sleep(5000);
            System.out.println("运动员都到达了，裁判在" + /*TimerUtils.getSecond() + */"秒说准备。。。");
            waitTag.countDown();

            Thread.sleep(5000);
            System.out.println("运动员都已准备，裁判在" + /*TimerUtils.getSecond() + */"秒说起跑。。。");
            beginTag.countDown();

            //裁判等运动员都到达终点后，宣布比赛结束
            endTag.await();
            System.out.println("运动员都已到达终点，裁判宣布比赛结束");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
运动员类
 */
class ThreadSportsMan extends Thread{
    private CountDownLatch comeTag;
    private CountDownLatch waitTag;
    private CountDownLatch beginTag;
    private CountDownLatch endTag;

    ThreadSportsMan(CountDownLatch comeTag, CountDownLatch waitTag, CountDownLatch beginTag, CountDownLatch endTag){
        this.comeTag = comeTag;
        this.waitTag = waitTag;
        this.beginTag = beginTag;
        this.endTag = endTag;
    }
    @Override
    public void run() {
        String threadName = String.valueOf(Thread.currentThread().getId() + 1 - 10);
        try {

//            String beginSecond  = TimerUtils.getSecond();
            System.out.println(threadName + "号运动员准备出发去起跑点。。。"/* + "开始时间为： " + beginSecond + "秒"*/);

            //去比赛场地途中
            Thread.sleep((int) (Math.random() * 10000));

            System.out.println(threadName + "号运动员到达起跑点"/* + "， 用时： " + TimerUtils.getIntervalSecond(beginSecond) + "秒"*/);
            comeTag.countDown();

            System.out.println(threadName + "号运动员等待裁判说准备。。。"/* + "等待开始时间： " + TimerUtils.getSecond() + "秒"*/);
            waitTag.await();

            System.out.println(threadName + "号运动员等待裁判说起跑。。。"/* + "等待开始时间： " + TimerUtils.getSecond() + "秒"*/);
            beginTag.await();

            //裁判说起跑后
//            beginSecond = TimerUtils.getSecond();
            System.out.println(threadName + "号运动员开始跑。。。" /*+ "起跑时间为： " + beginSecond + "秒"*/);

            //运动员不同起跑速度
            Thread.sleep((int)(Math.random() * 10000));

            System.out.println(threadName + "号运动员已跑到终点" /*+ "，用时： " + TimerUtils.getIntervalSecond(beginSecond) + "秒"*/);
            //比赛结束
            endTag.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
