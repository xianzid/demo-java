package com.example.demo.properties;

public enum UserType {
    CUSTOMER(1, "customer"), INSURANCE(2, "insuranceComp"), PLATFORM(3, "platform"), AGENT(4, "agent"),
    CHILD(5, "child"), TEACHER(6, "teacher"), STUDENT(7, "student");
    private int index;
    private String value;
    UserType(int index, String value){
        this.index = index;
        this.value = value;
    }
}
