package com.example.demo.test.jvm;

public class MemoryTest {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();//默认内存大小-Xms
        long maxMemory = runtime.maxMemory();//最大内存大小-Xmx
        System.out.println("Mac total memory is " + totalMemory);
        System.out.println("Mac max memory is " + maxMemory);
    }
}
