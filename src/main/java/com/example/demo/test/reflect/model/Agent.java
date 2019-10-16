package com.example.demo.test.reflect.model;

public class Agent extends User implements UserPrint {
    public String union;

    @Override
    public void getInfo() {
        System.out.println("我是推广代理人员");
    }
}
