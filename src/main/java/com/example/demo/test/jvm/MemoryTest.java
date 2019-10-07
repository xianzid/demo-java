package com.example.demo.test.jvm;

public class MemoryTest {
    public static void main(String[] args) {
        createBadGC();
    }

    /**
     * 获取内存大小设置数
     */
    private static void getMemory(){
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();//默认内存大小-Xms
        long maxMemory = runtime.maxMemory();//最大内存大小-Xmx
        System.out.println("Mac total memory is " + totalMemory);
        System.out.println("Mac max memory is " + maxMemory);
    }

    /**
     * 创建一个万恶的代码
     */
    private static void createBadGC(){
        String s = "";
        for (int i = 0 ; i < Integer.MAX_VALUE ; i++){
            s = i + s;
            s.intern();
        }
    }
}
