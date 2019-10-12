package com.example.demo.test.reflect;

import java.lang.reflect.Field;

/**
 * 反射-属性
 */
public class GetFiledInfo {
    public static void main(String[] args) {
        try {
            final Class<?> cls = Class.forName("com.example.demo.test.reflect.model.Customer");
            final Field[] declaredFields = cls.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                System.out.println(declaredField.toString());
            }
            System.out.println("");
            //只能获取到父类公共属性
            final Field[] fields = cls.getFields();
            for (Field field : fields) {
                System.out.println(field.toString());
            }
            System.out.println("");
            //怎么获取父类私有方法
            final Field[] superFields = cls.getSuperclass().getDeclaredFields();
            for (Field superField : superFields) {
                System.out.println(superField.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
