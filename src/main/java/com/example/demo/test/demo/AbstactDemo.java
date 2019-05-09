package com.example.demo.test.demo;

public class AbstactDemo {
    public static void main(String[] args) {
        Behaive b1 = new Robot();
        Behaive b2 = new Human();
        Behaive b3 = new Pig();
        fun(b1);
        fun(b2);
        fun(b3);
    }

    public static void fun(Behaive behaive){
        behaive.command(Behaive.EAT);
        behaive.command(Behaive.SLEEP);
        behaive.command(Behaive.WORK);
    }
}

abstract class Behaive{
    public static final int EAT=1;
    public static final int SLEEP=5;
    public static final int WORK=7;
    public abstract void eat();
    public abstract void sleep();
    public abstract void work();
    void command(int flag){
        switch (flag){
            case EAT:
                eat();
                break;
            case SLEEP:
                sleep();
                break;
            case WORK:
                work();
                break;
            case EAT+SLEEP:
                eat();
                sleep();
                break;
            case SLEEP+WORK:
                sleep();
                work();
                break;
            case EAT+SLEEP+WORK:
                eat();
                work();
                sleep();
                break;
        }
    }
}

class Robot extends Behaive{
    @Override
    public void eat() { }

    @Override
    public void sleep() {
        System.out.println("机器人正在充电");
    }

    @Override
    public void work() {
        System.out.println("机器人正在工作");
    }
}

class Human extends Behaive{

    @Override
    public void eat() {
        System.out.println("人类正在吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("人类正在睡觉");
    }

    @Override
    public void work() {
        System.out.println("人类正在工作");
    }
}

class Pig extends Behaive{

    @Override
    public void eat() {
        System.out.println("猪正在吃饭");
    }

    @Override
    public void sleep() {
        System.out.println("猪正在睡觉");
    }

    @Override
    public void work() { }
}


