package com.example.demo.test.reflect.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FruitFactory {
    /**
     * 获取要反射的对象的类名
     * @return "包名+类名"
     */
    public String className();
}
