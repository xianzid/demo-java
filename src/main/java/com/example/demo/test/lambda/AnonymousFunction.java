package com.example.demo.test.lambda;

/**
 * 匿名函数
 */
public class AnonymousFunction {

    /**
     * jdk8之前的写法
     */
    void oldWitten1(){
        MathOperation addition = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
    }

    /**
     * 使用了lambda表达式的写法
     */
    void newWitten1(){
        //类型有声明，不用大括号及返回语句
        MathOperation addition = (int a, int b) -> a + b;
        //类型不声明
        MathOperation subtraction = (a, b) -> a - b;
        //大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        //不用括号的-单参数
        GreetingService greetingService1 = message -> {
            System.out.println("hello " + message);
        };
        //不用大括号及返回语句
        GreetingService greetingService2 = message -> System.out.println("Hello " + message);
    }
}

interface MathOperation{
    int operation(int a, int b);
}

interface GreetingService{
    void sayMessage(String message);
}
