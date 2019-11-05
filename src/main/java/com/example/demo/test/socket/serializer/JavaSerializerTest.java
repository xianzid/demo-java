package com.example.demo.test.socket.serializer;

import com.example.demo.model.Student;

public class JavaSerializerTest {
    public static void main(String[] args) {
        ISerializer serializer = new JavaSerializer();
        Student student = new Student("Bob", 21);

        //序列化：对象转成字节数组
        byte[] stuBytes = serializer.serializer(student);

        //反序列化：字节数组转成对象
        Student resultStu = serializer.doSerializer(stuBytes, Student.class);

        System.out.println(resultStu.toString());
    }
}
