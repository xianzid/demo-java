package com.example.demo.model;

import com.example.demo.test.reflect.model.User;

public class Platform extends User implements UserPrint{
    @Override
    public void getInfo() {
        System.out.println("我是平台人员");
    }
}
