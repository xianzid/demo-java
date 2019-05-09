package com.example.demo.test.demo;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int data1 [] = new int[]{4,5,3,2,1};
        //print(data);
        System.out.println("******开始排序******");
        long time1 = System.nanoTime();
        //bubbleSort(data1);
        invertSort(data1);
        printUseTime(time1);
        /*int data2 [] = new int[]{1,2,4,3,5};
        time1 = System.nanoTime();
        selfSort(data2);
        printUseTime(time1);*/
        print(data1);
    }

    /**
     * 倒置排序
     * 排序用时：4703
     * @param data
     */
    private static void invertSort(int data []){
        int tail = data.length-1;
        int circulate = data.length % 2;

        for(int head = 0 ; head <= circulate ; head ++){
            int temp = data[head];
            data[head] = data[tail];
            data[tail] = temp;
            tail -- ;
        }
    }

    /**
     * 打印用时
     * time1为初始时间
     * @param time1
     */
    private static void printUseTime(long time1){
        long time2 = System.nanoTime();
        System.out.printf("排序用时：%d%n", (time2-time1));
    }

    /**
     * 经典冒泡排序
     * 排序用时：4492
     * @param data
     */
    private static void bubbleSort(int data []){
        int dataLength = data.length;
        for(int i=0;i<dataLength;i++){
            for(int j=0;j<dataLength-1;j++){
                if(data[j] > data[j+1]){
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
    }

    /**
     * 虽然排序轮数少，但增加判断，造成时间更长，如果是数据量很大的情况增加判断才会是合算的
     * 排序经过2轮 排序用时：120286
     * 排序经过5轮 排序用时：200977
     * @param data
     */
    private static void selfSort(int data []){
        int dataLength = data.length;
        boolean isOk = false;//是否完成排序
        int i = 0;//排序类几轮
        //排序未完成并且排序轮次数没达到数组长度时进行排序
        while (!isOk && i<dataLength){
            int sortNumber = 0;//每轮操作置换次数初始化为0
            for(int j = 0; j<dataLength-1;j++){
                if (data[j] > data[j+1]){
                    int temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                    sortNumber ++ ;
                }
            }
            i++;//加一轮
            //此轮排序时并未操作置换，说明已排序完成
            if (0 == sortNumber){
                isOk = true;
            }
        }
        System.out.printf("排序经过%d轮%n", i);
    }

    private static void print(int data[]){
        System.out.println("数据如下：");
        for (int temp : data){
            System.out.println(temp);
        }
    }
}
