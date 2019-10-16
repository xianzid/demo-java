package com.example.demo.test.reflect.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyFlag {
    public String name() default "注解";
    public String value();
}
