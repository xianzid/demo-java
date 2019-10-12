package com.example.demo.test.reflect;

import com.example.demo.test.reflect.model.Dept;
import com.example.demo.test.reflect.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 方法信息
 */
public class GetMethodInfo {
    public static void main(String[] args) {
        //格式：属性名:值|。。。
        String str = "id:1001|deptName:信息部|deptCode:1001|supior:0|level:2|createTime:2019-10-12|account:132.91|isSuper:false";
        try {
            methodUtil(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("");
        methodUtil();
    }

    /**
     * 反射基本-方法信息获取打印
     */
    private static void baseMethodPrint(){
        final Class<Dept> deptClass;
        try {
            deptClass = (Class<Dept>) Class.forName("com.example.demo.test.reflect.model.Dept");
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
        final String[] fieldMaps = str.split("\\|");
        final Class<?> cls = Class.forName("com.example.demo.test.reflect.model.Dept");
        Object obj = cls.newInstance();
        Method method;
        for (String fieldMap : fieldMaps) {
            //属性名:属性值
            final String[] temp = fieldMap.split(":");
            if (temp.length != 2){
                continue;
            }

            String methodName = StringUtils.initFiledName(temp[0]);
            Field field = cls.getDeclaredField(temp[0]);
            Class paramType = field.getType();
            method = cls.getDeclaredMethod("set" + methodName, paramType);

            String methodParam = temp[1];
            method.invoke(obj, changeType(paramType, methodParam));

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
            final Class<?> aClass = Class.forName("com.example.demo.test.reflect.model.Dept");
            final Object obj = aClass.newInstance();
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
                str.append(method.invoke(obj)).append(" | ");
            }
            if (str.length() > 2){
                str.delete(str.length()-2, str.length());
            }
            System.out.println(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 参数值类型转换
     * @param classType
     * @param paramValue
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ParseException
     */
    private static Object changeType(Class<?> classType, String paramValue) throws IllegalAccessException, InstantiationException, ParseException {
        final String paramType = classType.getSimpleName();
        if ("string".equalsIgnoreCase(paramType)){
            return paramValue;
        } else if("int".equalsIgnoreCase(paramType) || "integer".equalsIgnoreCase(paramType)){
            return Integer.parseInt(paramValue);
        } else if("date".equalsIgnoreCase(paramType)){
            return new SimpleDateFormat("yyyy-MM-DD").parse(paramValue);
        } else if("BigDecimal".equalsIgnoreCase(paramType)){
            return new BigDecimal(paramValue);
        } else if("boolean".equalsIgnoreCase(paramType)){
            return Boolean.parseBoolean(paramValue);
        }
        return paramType;
    }
}
