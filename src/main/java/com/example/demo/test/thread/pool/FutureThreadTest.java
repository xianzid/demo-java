package com.example.demo.test.thread.pool;

import java.util.concurrent.*;

public class FutureThreadTest {
    public static void main(String[] args) {
        Callable<String> callable = getCallable();
        //线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        //设置返回处理
        Future<String> future = poolExecutor.submit(callable);
        //加线程
        MyThread thread = new MyThread();
        poolExecutor.execute(thread);
        poolExecutor.execute(thread);
        poolExecutor.execute(thread);
        try {
            //获取返回值
            String result = future.get();
            System.out.println("" + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /*

     */
    private static Callable<String> getCallable(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.currentThread().setName("1234567");
                System.out.println("call");
                return "result";
            }
        };
        return callable;
    }
}

class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println("run" + Thread.currentThread().getName());
    }
}
