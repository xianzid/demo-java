package com.example.demo.properties;

public enum UserType {
    CHILD(1, "child"), TEACHER(2, "teacher"), STUDENT(3, "student");
    private int index;
    private String value;
    UserType(int index, String value){
        this.index = index;
        this.value = value;
    }
}
