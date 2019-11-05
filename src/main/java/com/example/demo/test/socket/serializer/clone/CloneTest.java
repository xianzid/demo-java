package com.example.demo.test.socket.serializer.clone;

import java.io.IOException;

public class CloneTest {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Email email = new Email("20:00上课");
        PlanWork p1 = new PlanWork("1001", email);
        //浅克隆
        PlanWork p2 = p1.clone();
        p2.setWorkName("1002");
        p2.getEmail().setContent("10:30上课");
        System.out.println(p1.toString());
        System.out.println(p2.toString());

        //重置
        p1.getEmail().setContent("20:00上课");
        System.out.println("");

        //深克隆
        PlanWork p3 = p1.deepClone();
        p3.setWorkName("1003");
        p3.getEmail().setContent("19:30上课");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
    }
}
