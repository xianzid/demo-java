package com.example.demo.model;

import com.example.demo.test.reflect.model.User;

public class Agent extends User implements UserPrint{
    private String union;

    @Override
    public void getInfo() {
        System.out.println("我是推广代理人员");
    }

    public String getUnion() {
        return union;
    }

    public void setUnion(String union) {
        this.union = union;
    }
}
