package com.example.demo.test.pattern;

public class CreatetionTest {
    public static void main(String[] args) {
        /**
         * 简单工厂模式：一个园丁
         */
        //simpleFactory();

        /**
         * 工厂方法模式：不同的水果有不同的园丁
         */
        factoryMethod();

    }

    /**
     * 简单工厂模式
     */
    private static void simpleFactory(){
        StaticFactory.getFruit(StaticFactory.APPLE);
        StaticFactory.getFruit(StaticFactory.ORANGE);
        StaticFactory.getFruit(StaticFactory.BANANA);
    }

    private static void factoryMethod(){
        new AppleFactory().getFruit(AppleFactory.RBA);
        new AppleFactory().getFruit(AppleFactory.SDA);
        new OrangeFactory().getFruit(OrangeFactory.STO);
        new OrangeFactory().getFruit(OrangeFactory.HYO);
    }
}

/**
 * 简单工厂模式开始
 */
interface SimpleFruit{}
class SimpleApple implements SimpleFruit{
    SimpleApple(){
        System.out.println("苹果：红富士");
    }
}
class SimpleOrange implements SimpleFruit{
    SimpleOrange(String name){
        System.out.println("橘子：" + name);
    }
}
class SimpleBanana implements SimpleFruit{
    SimpleBanana(){
        System.out.println("香蕉：泰蕉");
    }
}
//园丁负责摘水果
class StaticFactory{
    final static int APPLE=1;
    final static int ORANGE=2;
    final static int BANANA=3;

    public static SimpleFruit getFruit(int type){
        if (APPLE == type){
            return new SimpleApple();
        } else if (ORANGE == type){
            return new SimpleOrange("沙田橘");
        } else if (BANANA == type){
            return new SimpleBanana();
        }
        return null;
    }
}
/**
 * 简单工厂模式结束
 */


/**
 * 工厂方法模式开始
 */
interface Fruit{}
interface Apple extends Fruit{}
class SDApple implements Apple{
    SDApple(){
        System.out.println("山东沙苹果");
    }
}
class RBApple implements Apple{
    RBApple(){
        System.out.println("日本红富士");
    }
}
interface FruitFactory{
    Fruit getFruit(int type);
}
class AppleFactory implements FruitFactory{
    final static int SDA = 1;
    final static int RBA = 2;
    public Apple getFruit(int type){
        if (SDA == type){
            return new SDApple();
        } else if (RBA == type){
            return new RBApple();
        }
        return null;
    }
}
interface Orange extends Fruit{}
class STOrange implements Orange{
    STOrange(){
        System.out.println("沙田橘");
    }
}
class HYOrange implements Orange{
    HYOrange(){
        System.out.println("黄岩蜜橘");
    }
}
class OrangeFactory implements FruitFactory{
    final static int STO = 1;
    final static int HYO = 2;
    public Orange getFruit(int type){
        if (STO == type){
            return new STOrange();
        } else if (HYO == type){
            return new HYOrange();
        }
        return null;
    }
}

/**
 * 工厂方法模式结束
 */