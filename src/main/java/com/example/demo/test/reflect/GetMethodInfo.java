package com.example.demo.test.reflect;

import com.example.demo.test.reflect.object.Dept;
import com.example.demo.test.reflect.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 方法信息
 */
public class GetMethodInfo {
    public static void main(String[] args) {
        //格式：属性名:值|。。。
        String str = "id:1001|deptName:'信息部'|deptCode:'1001'|supior:0|level:2";
        try {
            methodUtil(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射基本-方法信息获取打印
     */
    private static void baseMethodPrint(){
        final Class<Dept> deptClass;
        try {
            deptClass = (Class<Dept>) Class.forName("com.example.demo.test.reflect.object.Dept");
            //会把父类Object的信息也打印出来
            for (Method method : deptClass.getMethods()) {
                System.out.println(method.toString());
            }
            //只显示本类声明的方法
            for (Method method : deptClass.getDeclaredMethods()) {
                System.out.println(method.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法工具类编写：赋值
     * 接收格式：属性名: 属性值 | 属性名: 属性值| 。。。
     */
    private static void methodUtil(String str) throws Exception{
        final String[] fields = str.split("\\|");
        final Class<?> cls = Class.forName("com.example.demo.test.reflect.object.Dept");
        Object obj = cls.newInstance();
        Method method;
        for (String field : fields) {
            //属性名:属性值
            final String[] temp = field.split(":");
            if (temp.length != 2){
                continue;
            }
            final String methodName = StringUtils.initFiledName(temp[0]);
            String methodParam = temp[1];
            //String类型的
            if (-1 != methodParam.indexOf("'")){
                methodParam = methodParam.replaceAll("'","");
                method = cls.getDeclaredMethod("set" + methodName, String.class);
                method.invoke(obj, methodParam);
            } else {
                //int类型的
                method = cls.getDeclaredMethod("set" + methodName, int.class);
                method.invoke(obj, Integer.valueOf(methodParam).intValue());
            }
        }
        System.out.println(obj);
    }

    /**
     * 方法工具类编写：
     * 输出属性及值
     * 属性名: 属性值 | 属性名: 属性值| 。。。
     */
    private static void methodUtil(){
        StringBuffer str = new StringBuffer();
        try {
            final Class<?> aClass = Class.forName("com.example.demo.test.reflect.object.Dept");
            final Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                final String name = field.getName();
                final Method method;
                try {
                    method = aClass.getDeclaredMethod("get" + StringUtils.initFiledName(name));
                } catch (NoSuchMethodException e){
                    continue;
                }
                str.append(name).append(": ");
                str.append(method.invoke(aClass.newInstance())).append(" | ");
            }
            if (str.length() > 2){
                str.delete(str.length()-2, str.length());
            }
            System.out.println(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?> getObjType(String value){
        if (-1 != value.indexOf("'")){
            return String.class;
        }
        return int.class;
    }
}
