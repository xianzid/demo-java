package com.example.demo.test.socket.serializer;

import com.example.demo.model.Student;
import com.example.demo.test.socket.serializer.clone.Email;
import com.example.demo.test.socket.serializer.clone.PlanWork;

public class SerializerTest {
    public static void main(String[] args) {
        //java序列化
        ISerializer serializer = new JavaSerializer();
        Student student = new Student("Bob", 21);

        //序列化：对象转成字节数组
        byte[] stuBytes = serializer.serializer(student);

        //反序列化：字节数组转成对象
        Student resultStu = serializer.doSerializer(stuBytes, Student.class);

        System.out.println(resultStu.toString());
        System.out.println("");

        //File序列化
        /*ISerializer serializer2 = new FileSerializer();
        byte[] fileBytes = serializer2.serializer(student);
        Student fileResult = serializer2.doSerializer(fileBytes, Student.class);
        System.out.println(fileResult.toString());*/
        System.out.println("");

        //java手动序列化
        PlanWork planWork = new PlanWork("ABC", new Email("8:00上班"));
        planWork.setDecode("20191103");
        byte[] planBytes = serializer.serializer(planWork);
        PlanWork resultPlan = serializer.doSerializer(planBytes, PlanWork.class);
        System.out.println(resultPlan.toString());
        System.out.println("");

        //XML序列化
        ISerializer serializer3 = new XMLSerializer();
        byte[] xmlBytes = serializer3.serializer(student);
        System.out.println(new String(xmlBytes));

        Student xmlResult = serializer3.doSerializer(xmlBytes, Student.class);
        System.out.println(xmlResult.toString());
    }
}
