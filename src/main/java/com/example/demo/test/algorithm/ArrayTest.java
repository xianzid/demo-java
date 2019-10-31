package com.example.demo.test.algorithm;

import java.util.HashSet;
import java.util.Set;

public class ArrayTest {
    public static void main(String[] args) {
        sortOrder();
    }

    /**
     * 找出数组中任意一个重复的数字
     */
    private static void getDuplicateFromArray() {
        int[] arrays = {2, 3, 1, 0, 2, 5};
        Set judgeSet = new HashSet();
        int duplicateNumber = 0;
        /*
        HashSet是Set，不允许重复，增加时返回值为布尔值，据此快速找到重复的数字。查询复杂度为O(n)
         */
        System.out.println("Begin select in " + System.currentTimeMillis());
        for (int array : arrays) {
            boolean isContain = judgeSet.add(array);
            System.out.printf("Select is contain : %b , number is : %d%n ", isContain, array);
            if (!isContain) {
                duplicateNumber = array;
                break;
            }
        }
        System.out.println("End select in " + System.currentTimeMillis());
        System.out.println("Result number is : " + duplicateNumber);
    }

    /**
     * 顺序排序
     */
    private static void sortOrder() {
        int[] nums = {10, 3, 40, 8, 12};
        int length = nums.length;
        int averageL = length / 2;
        int averageNum = 10;
        boolean isOK = false;
        //循环几轮
        int cicle = 0;
        int i = 0;
        while (!isOK && i < length) {
            int sortNumber = 0;//每轮操作置换次数初始化为0
            for (int j = 0; j < length - 1; j++) {
                if (j < averageL && nums[j] > averageNum) {
                    int temp = nums[j];
                    nums[j] = nums[averageL + 1];
                    nums[averageL + 1] = temp;
                    sortNumber++;
                }
                if (j > averageL && nums[j] < averageNum) {
                    int temp = nums[j];
                    nums[j] = nums[averageL - 1];
                    nums[averageL - 1] = temp;
                    sortNumber++;
                }
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    sortNumber++;
                }
                System.out.printf("本轮操作轮%d次%n", sortNumber);
                print(nums);
                cicle++;
            }
            if (0 == sortNumber) {
                isOK = true;
            }
            i++;
        }
        System.out.printf("排序经过%d轮%n", cicle);
        print(nums);
    }

    private static void print(int data[]) {
        System.out.println("数据如下：");
        for (int temp : data) {
            System.out.print(temp + "，");
        }
        System.out.println("");
    }

    


}
