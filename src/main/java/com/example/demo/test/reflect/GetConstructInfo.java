package com.example.demo.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 通过反射获取并拼接构造方法信息
 */
public class GetConstructInfo {
    public static void main(String[] args) {
        try {
            final Class<?> cls = Class.forName("com.example.demo.test.reflect.object.Dept");
            StringBuffer str = new StringBuffer();

            //获取构造方法
            final Constructor<?>[] cons = cls.getConstructors();
            for (Constructor<?> con : cons) {
                
                //获取修饰符、名称
                str.append(Modifier.toString(con.getModifiers())).append(" ");
                str.append(con.getName()).append("(");
                //获取入参
                if (con.getParameterCount() > 0){
                    final Class<?>[] params = con.getParameterTypes();
                    for (Class<?> param : params) {
                        str.append(param.getSimpleName()).append(", ");
                    }
                    str.delete(str.length() - 2, str.length());
                }
                str.append(")");
                //获取异常
                final Class<?>[] exceptions = con.getExceptionTypes();
                if (exceptions.length > 0){
                    str.append(" throws ");
                    for (Class<?> ex : exceptions) {
                        str.append(ex.getSimpleName()).append(", ");
                    }
                    str.delete(str.length() - 2, str.length());
                }
                str.append("\n");
            }

            System.out.println(str);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
