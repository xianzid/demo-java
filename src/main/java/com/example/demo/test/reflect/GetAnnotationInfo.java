package com.example.demo.test.reflect;

import com.example.demo.test.reflect.annotation.FruitFactory;
import com.example.demo.test.reflect.annotation.MyFlag;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射-获取注解信息
 */
public class GetAnnotationInfo {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("com.example.demo.test.reflect.model.Emp");
            //获取类的注解
            //@SuppressWarnings("emp")是编译时才有用的
            final Annotation[] annotations = cls.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

            System.out.println("");
            //获取方法上的注解
            //@Override是编译时才有用的
            final Method[] methods = cls.getMethods();
            for (Method method : methods) {
                final Annotation[] annotations1 = method.getAnnotations();
                for (Annotation annotation : annotations1) {
                    System.out.println(annotation);
                }
            }
            System.out.println("");

            //获取自定义注解
            final Field[] declaredFields = cls.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                final Annotation[] annotations1 = declaredField.getAnnotations();
                for (Annotation annotation : annotations1) {
                    MyFlag myFlag = (MyFlag)annotation;
                    System.out.println(myFlag.name() + ": " + myFlag.value());
                }
            }

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("");

        getApple();
    }

    @FruitFactory(className = "com.example.demo.test.reflect.Apple")
    private static void getApple(){
        final Class<GetAnnotationInfo> getAnnotationInfoClass = GetAnnotationInfo.class;
        try {
            final Method getApple = getAnnotationInfoClass.getDeclaredMethod("getApple", null);
            final FruitFactory annotation = getApple.getAnnotation(FruitFactory.class);

            Fruit fruit = (Fruit)Class.forName(annotation.className()).newInstance();
            fruit.eat();
        } catch (NoSuchMethodException e) {
            System.out.println("没找到getApple()方法");
        } catch (ClassNotFoundException e) {
            System.out.println("没找到类");
        } catch (IllegalAccessException e) {
            System.out.println("非法存取");
        } catch (InstantiationException e) {
            System.out.println("实例化异常");
        }
    }
}

interface Fruit{
    public void eat();
}
class Apple implements Fruit{
    @Override
    public void eat() {
        System.out.println("********吃苹果*******");
    }
}
class Orange implements Fruit{
    @Override
    public void eat() {
        System.out.println("********吃橘子*******");
    }
}
