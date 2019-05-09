package com.example.demo.test.algorithm;

import java.util.HashSet;
import java.util.Set;

public class ArrayTest {
    public static void main(String[] args) {
        getDuplicateFromArray();
    }

    /**
     * 找出数组中任意一个重复的数字
     */
    private static void getDuplicateFromArray(){
        int[] arrays = {2,3,1,0,2,5};
        Set  judgeSet = new HashSet();
        int duplicateNumber = 0;
        /*
        HashSet是Set，不允许重复，增加时返回值为布尔值，据此快速找到重复的数字。查询复杂度为O(n)
         */
        System.out.println("Begin select in " + System.currentTimeMillis());
        for (int array : arrays){
            boolean isContain = judgeSet.add(array);
            System.out.printf("Select is contain : %b , number is : %d%n ", isContain, array);
            if (!isContain){
                duplicateNumber = array;
                break;
            }
        }
        System.out.println("End select in " + System.currentTimeMillis());
        System.out.println("Result number is : " + duplicateNumber);
    }
}
