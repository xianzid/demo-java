package com.example.demo.test.reflect;

import com.example.demo.test.reflect.object.Dept;

/**
 * 获取类类型的三种方式
 */
public class BaseReflectMethod {
    public static void main(String[] args) {
        getClassMethod();
        getNewObject();
    }

    /**
     * 获取Class的三种方式
     */
    private static void getClassMethod(){
        //反射基础-Class对象
        Class aClass;

        /**
         * 必须类已存在，才能使用（否则编译就会出错）
         */
        //1.通过对象的getClass()方法
        //几乎不会用到
        Dept dept = new Dept();
        aClass = dept.getClass();

        //2.通过对象的class属性
        //只有框架技术上使用
        aClass = Dept.class;

        /**
         * 类不存在，编译也不会报错；后续可以添加该类
         */
        //3.通过Class的forName()方法
        //jdbc有用
        try {
            aClass = Class.forName("com.example.demo.test.reflect.object.Dept");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 类实例化的两种方式
     */
    private static void getNewObject(){
        //1。new关键字
        Dept dept = new Dept();
        //2。Class对象的newInstance()方法
        try {
            Class<?> cls = Class.forName("com.example.demo.test.reflect.object.Dept");
            Object  obj = cls.newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
