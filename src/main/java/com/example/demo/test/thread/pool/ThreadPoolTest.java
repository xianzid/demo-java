package com.example.demo.test.thread.pool;


import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>());
    }

    private static void test2() {
        /**
         * 线程名
         */
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread();
                String[] strs = thread.getName().split("-");
                String id = "0";
                if ((strs.length >= 2)) {
                    id = strs[1];
                }
                thread.setName("Thread" + id);
                return thread;
            }
        };

        /**
         * 饱和策略
         */
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(Thread.currentThread().getName() + "被拒绝，线程已饱和！");
            }
        };

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 10, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5), threadFactory, handler);

        /**
         * 运行操作
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println((Thread.currentThread().getName() + "开始启动。。。"));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println((Thread.currentThread().getName() + "结束工作。。。"));
            }
        };

//        for (int i = 0; i < 10; i++) {
            pool.execute(runnable);
//        }

        /*Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            pool.execute(runnable);
        }*/

    }
}
