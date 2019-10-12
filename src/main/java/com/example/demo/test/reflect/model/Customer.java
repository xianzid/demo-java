package com.example.demo.test.reflect.model;

public class Customer extends User implements UserPrint{
    public String carDriver;
    public String bankAccount;
    @Override
    public void getInfo() {
        System.out.println("我是用户");
    }
}
