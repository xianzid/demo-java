package com.example.demo.test.socket.serializer;

import com.example.demo.model.Student;
import com.example.demo.test.socket.serializer.clone.Email;
import com.example.demo.test.socket.serializer.clone.PlanWork;

public class JavaSerializerTest {
    public static void main(String[] args) {
        ISerializer serializer = new JavaSerializer();
        Student student = new Student("Bob", 21);

        //序列化：对象转成字节数组
        byte[] stuBytes = serializer.serializer(student);

        //反序列化：字节数组转成对象
        Student resultStu = serializer.doSerializer(stuBytes, Student.class);

        System.out.println(resultStu.toString());

        PlanWork planWork = new PlanWork("ABC", new Email("8:00上班"));
        planWork.setDecode("20191103");
        byte[] planBytes = serializer.serializer(planWork);
        PlanWork resultPlan = serializer.doSerializer(planBytes, PlanWork.class);
        System.out.println(resultPlan.toString());
    }
}
