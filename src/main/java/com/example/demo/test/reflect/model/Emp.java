package com.example.demo.test.reflect.model;

import com.example.demo.test.reflect.annotation.MyFlag;

/**
 * 反射-注解的测试bean类
 */
@SuppressWarnings("emp")
@Deprecated
public class Emp {

    @MyFlag(value = "部门名称")
    private String empName;

    @Deprecated
    @Override
    public String toString(){
        return this.empName;
    }
}

