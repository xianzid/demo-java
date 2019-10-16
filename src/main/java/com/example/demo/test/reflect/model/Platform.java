package com.example.demo.test.reflect.model;

public class Platform extends User implements UserPrint {
    @Override
    public void getInfo() {
        System.out.println("我是平台人员");
    }
}
