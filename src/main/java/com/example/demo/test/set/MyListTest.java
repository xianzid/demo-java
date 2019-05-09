package com.example.demo.test.set;

public class MyListTest {
    public static void main(String[] args) {
        testUseTimeBtweenMapAndList();
    }

    private static void testUseTimeBtweenMapAndList(){
        /**
         * 测试数组时间
         */
        System.out.println("--------数组测试开始-------");
        /*
        数组新增100个值
         */
        MyArray myArray = new MyArray();
        long time1 = System.nanoTime();
        for (int i = 1;i <=100;i++){
            myArray.addList(i);
        }
        long time2 = System.nanoTime();
        System.out.printf("批量新增%d条数据用时：%d%n", myArray.getLength(), (time2-time1));

        time1 = System.nanoTime();
        myArray.addList(101);
        time2 = System.nanoTime();
        System.out.printf("新增单条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        myArray.deleteList(1);
        time2 = System.nanoTime();
        System.out.printf("删除单条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        myArray.updateList(40, 110);
        time2 = System.nanoTime();
        System.out.printf("修改单条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        myArray.deleteList(3);
        myArray.deleteList(77);
        myArray.deleteList(12);
        myArray.deleteList(55);
        time2 = System.nanoTime();
        System.out.printf("删除多条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        int tempNum = myArray.getNum(40);
        time2 = System.nanoTime();
        System.out.printf("查询单条数据用时：%d, 查询结果为：%d%n", (time2-time1), tempNum);

        time1 = System.nanoTime();
        myArray.toString();
        time2 = System.nanoTime();
        System.out.printf("遍历数据用时：%d%n", (time2-time1));

        /**
         * 测试链表时间
         */
        System.out.println("----------链表测试开始---------");

        MyLinked myLinked = new MyLinked();
        time1 = System.nanoTime();
        for (int j = 0;j<100;j++){
            myLinked.addList(j);
        }
        time2 = System.nanoTime();
        System.out.printf("批量新增%d条数据用时：%d%n", myLinked.getLength(), (time2-time1));

        time1 = System.nanoTime();
        myLinked.addList(101);
        time2 = System.nanoTime();
        System.out.printf("新增单条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        myLinked.deleteList(3);
        time2 = System.nanoTime();
        System.out.printf("删除单条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        myLinked.updateList(40, 110);
        time2 = System.nanoTime();
        System.out.printf("修改单条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        myLinked.deleteList(10);
        myLinked.deleteList(77);
        myLinked.deleteList(12);
        myLinked.deleteList(55);
        time2 = System.nanoTime();
        System.out.printf("删除多条数据用时：%d%n", (time2-time1));

        time1 = System.nanoTime();
        tempNum = myLinked.getNum(40);
        time2 = System.nanoTime();
        System.out.printf("查询单条数据用时：%d, 查询结果为：%d%n", (time2-time1), tempNum);

        time1 = System.nanoTime();
        myLinked.toString();
        time2 = System.nanoTime();
        System.out.printf("遍历数据用时：%d%n", (time2-time1));

    }

}
