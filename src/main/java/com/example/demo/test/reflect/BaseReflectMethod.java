package com.example.demo.test.reflect;

import com.example.demo.test.reflect.object.Dept;

/**
 * 获取类类型的三种方式
 */
public class BaseReflectMethod {
    public static void main(String[] args) {
        Class aClass;

        //1.通过对象的getClass()方法
        Dept dept = new Dept();
        aClass = dept.getClass();

        //2.通过对象的class属性
        aClass = Dept.class;

        //3.通过Class的forName()方法
        try {
            aClass = Class.forName("com.example.demo.test.reflect.object.Dept");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
