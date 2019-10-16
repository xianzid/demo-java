package com.example.demo.model;

import com.example.demo.test.reflect.model.User;

public class InsuranceCmy extends User implements UserPrint{
    private String insurer;
    @Override
    public void getInfo() {
        System.out.println("我是保险公司人员");
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer;
    }
}
