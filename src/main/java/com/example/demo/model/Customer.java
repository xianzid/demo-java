package com.example.demo.model;

import com.example.demo.test.reflect.model.User;

public class Customer extends User implements UserPrint{
    private String carDriver;
    private String bankAccount;
    @Override
    public void getInfo() {
        System.out.println("我是用户");
    }

    public String getCarDriver() {
        return carDriver;
    }

    public void setCarDriver(String carDriver) {
        this.carDriver = carDriver;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
