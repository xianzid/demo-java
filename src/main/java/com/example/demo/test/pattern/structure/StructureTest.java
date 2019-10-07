package com.example.demo.test.pattern.structure;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class StructureTest {
    public static void main(String[] args) {
        /**
         * 适配器模式Adapter
         * 适配者：Reader
         * 被适配者：InputStream
         * 关系：无关联
         * 适配器：InputStreamReader
         */
        InputStream inputStream = null;
        Reader reader = new InputStreamReader(inputStream);

        /**
         * 装饰器模式Delegate
         * 装饰者：DataInputStream
         * 被装饰者：InputStream
         * 关系：层次关系 DataInputStream子类->filterInputStream父类->InputStream基类
         */
        inputStream = null;
        FilterInputStream filterInputStream = new DataInputStream(inputStream);

        /**
         * 享元模式FlyWight
         * 共享收据
         */
        //构造方法，会开辟两块内存空间，其中一块成为垃圾，并且该数据不会自动入池
        //直接赋值时会自动放入常量池中
        String value = new String("Hello");//Heap堆
        //手动入池，共享该"Hello"数据
        value.intern();

        //Integer.valueOf(value)会先从缓存中找，缓存为共享数据
        value = "123";
        Integer integer = Integer.valueOf(value);

        /**
         * 组合模式Composite
         * 关系：业务组合，业务对象之间可以有关或无关
         * 参考：CompositeELResolver、CompositeCacheManager（缓存管理，缓存有一级、二级缓存，不管一级二级都要做的事）
         */


        /**
         * 门面模式Facade
         * 关系：顺序执行 相对独立的业务 组合，业务对象之间可以有关或无关
         * 参考：springMVC.DispatcherServlet
         */
        new FacadeMethod1().save();
        new FacadeMethod2().up();

    }
}

/**
 * springCache简易实现
 */
class CompositeMethod{
    private interface CAManager{
        CA getCacheName(String name);
    }
    private interface Init{
        void setCache(CAManager caManager);
    }
    private class CompositeCacheManag implements CAManager, Init{
        List<CAManager> caManagers;
        CompositeCacheManag(){
            caManagers = new ArrayList();
        }
        public CA getCacheName(String name){
            for (CAManager caManager : caManagers){
                CA ca = caManager.getCacheName(name);
                if (null != ca){
                    return ca;
                }
            }
            return null;
        }
        public void setCache(CAManager caManager){}
    }
    private interface CA{}
    private class C1 implements CA{}
    private class C2 implements CA{}
    private class C1Init implements Init{
        public void setCache(CAManager caManager){

        }
    }
    private class C2Init implements Init{
        public void setCache(CAManager caManager){

        }
    }
    private class C1Manager implements CAManager{
        public CA getCacheName(String name){return null;}
    }
    private class C2Manager implements CAManager{
        public CA getCacheName(String name){return null;}
    }
}

/**
 * 门面模式
 * 不同业务 由 不同对象功能 实现 组合后的类
 */
class FacadeMethod1{
    private class ServiceA{
        void save(){}
    }
    private class ServiceB{
        void delete(){}
    }
    private ServiceA a;
    private ServiceB b;
    FacadeMethod1(){
        a = new ServiceA(); b = new ServiceB();
    }
    void save(){
        a.save();
        b.delete();
    }
}

/**
 * 门面模式
 * 按顺序执行 相同业务下 不同对象功能
 */
class FacadeMethod2{
    private class CPU{
        void up(){}
        void down(){}
    }
    private class Memory{
        void up(){}
        void down(){}
    }
    private class Disk{
        void up(){}
        void down(){}
    }
    CPU cpu; Memory memory; Disk disk;
    FacadeMethod2(){
        cpu = new CPU(); memory = new Memory(); disk = new Disk();
    }
    //有顺序的执行开启
    void up(){
        cpu.up(); memory.up(); disk.up();
    }
    void down(){
        cpu.down(); memory.down(); disk.down();
    }
}
