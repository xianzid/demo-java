package com.example.demo.test.lambda;

/**
 * 构造函数
 */
public class Constructor {
    /**
     * jdk8之前的写法
     */
    void oldWitten(){
//        Car car = new Car();
    }

    /**
     * 使用了lambda表达式之后的写法
     */
    void newWitten(){
        Car car = Car.create(Car::new);
    }
}

class Car{

    @FunctionalInterface
    interface Supplier<T> {
        T get();
    }

    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }
}
