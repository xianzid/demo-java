package com.example.demo.test.reflect.model;

public class InsuranceCmy extends User implements UserPrint{
    public String insurer;
    @Override
    public void getInfo() {
        System.out.println("我是保险公司人员");
    }
}
