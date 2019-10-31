package com.example.demo.test.reflect.util;

public class StringUtils {
    public static String initFiledName(String fileName){
        if (null == fileName || "".equals(fileName)){
            return "";
        }

        //就一位，直接大写
        if (1 == fileName.length()){
            return fileName.toUpperCase();
        }

        //isSuper->Super
        if (fileName.startsWith("is")){
            return fileName.substring(2);
        }

        //首字母大写
        return fileName.substring(0, 1).toUpperCase() + fileName.substring(1);
    }
}
