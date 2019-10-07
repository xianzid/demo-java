package com.example.demo.test.pattern.structure.proxy;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.awt.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        targetProxyTest();
    }

    private static void targetProxyTest(){
        UserService target = new UserServiceImpl();
        System.out.println(target.getClass());
        UserService proxy = (UserService) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        int rs = proxy.save(null);
        System.out.println("save " + ((-1 == rs)? false : true));
        rs = proxy.save(new User());
        System.out.println("save " + ((-1 == rs)? false : true));
    }

    private static void imageProxyTest(){
        LoadFromDisk loadFromDisk = new LoadFromDisk();
        /*
        //静态代理
        LoadImageProxy proxy = new LoadImageProxy(loadFromDisk);
        proxy.loadImage("test.png");
        */
        //动态代理
        LoadImage loadImage = (LoadImage) Proxy.newProxyInstance(
                LoadImage.class.getClassLoader(),//当前目标对象使用类加载器，获取加载器的方法是固定的
                new Class[]{LoadImage.class},//目标对象实现的接口类型，使用范型方法确认类型
                new DynamicProxyHandler(loadFromDisk));//事件处理，把当前目标对象的方法作参数传入
        loadImage.loadImage("/tmp/test.png");
    }
}

/**
 * 创建一个jdk动态代理类
 */
class ProxyFactory{
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 生成一个目标对象实例
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(target, args);
                    }
                }
        );
    }
}

/**
 * 用Cglib对类做动态代理
 */
class ProxyFactoryCglibTest{
    public static void main(String[] args) {
        UserDao target = new UserDao();
        System.out.println(target.getClass());
        UserDao proxy = (UserDao)new ProxyFactoryCglib(target).getProxyInstance();
        System.out.println(target.getClass());
        proxy.save();
    }
}
class ProxyFactoryCglib{
    Object target;
    public ProxyFactoryCglib(Object target) {
        this.target = target;
    }
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2。设置父类
        en.setSuperclass(target.getClass());
        //3。设置回调函数
        en.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                return method.invoke(target, args);
            }
        });
        //4。创建子类（代理对象）
        return en.create();
    }
}
class UserDao{
    void save(){
        System.out.println("---------已经保存数据！-------");
    }
}
/**
 * Cglib动态代理例子结束
 */

interface LoadImage{
    Image loadImage(String name);
}

class LoadFromDisk implements LoadImage{
    @Override
    public Image loadImage(String name) {
        System.out.println("load name:'"+name+"' image from disk");
        return null;
    }
}

class LoadImageProxy implements LoadImage{
    LoadImage loadImageReal;
    public LoadImageProxy(LoadImage loadImageReal) {
        this.loadImageReal = loadImageReal;
    }
    @Override
    public Image loadImage(String name) {
        return loadImageReal.loadImage(name);
    }
}

class DynamicProxyHandler implements InvocationHandler{
    private Object proxyObject;
    public DynamicProxyHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //把目标/实际对象生成代理对象
        //运用反射执行目标对象方法
        return method.invoke(proxyObject, args);
    }
}
