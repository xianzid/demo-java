package com.example.demo.test.reflect;

/**
 * 反射实现了面向接口的概念，同样的功能用接口实现，与工厂模式一样
 */
public class ToAnInterface {
    public static void main(String[] args) {
        reflectImp();
        factoryImp();
    }

    /**
     * 反射的实现
     */
    private static void reflectImp(){
        new UserFactory().getUser("com.example.demo.test.reflect.Customer");
    }

    /**
     * 简单工厂模式的实现
     */
    private static void factoryImp(){
        new UserFactory().getUser(UserFactory.CMP);
    }
}

class UserFactory{
    final static int CUS = 1;
    final static int CMP = 2;
    final static int PLATFORM = 2;
    final static int AGENT = 2;

    public User getUser(String className){
        User user = null;
        final Class<?> aClass;
        try {
            aClass = Class.forName(className);
            user = (User)aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUser(int type){
        User user = null;
        if (CUS == type){
            return new Customer();
        } else if (CMP == type){
            return new InsuranceCmy();
        } else if (PLATFORM == type){
            return new Platform();
        } else if (AGENT == type){
            return new Agent();
        }
        return user;
    }
}

interface User{ }
class Customer implements User{
    Customer(){
        System.out.println("我是用户");
    }
}
class InsuranceCmy implements User{
    InsuranceCmy(){
        System.out.println("我是保险公司人员");
    }
}
class Platform implements User{
    Platform(){
        System.out.println("我是平台人员");
    }
}
class Agent implements User{
    Agent(){
        System.out.println("我是推广代理人员");
    }
}




