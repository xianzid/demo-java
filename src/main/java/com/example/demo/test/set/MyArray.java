package com.example.demo.test.set;

import java.util.Arrays;

/**
 * 自己写的数组列表
 */
public class MyArray implements MyList{
    //数组对象
    private int[] array;
    //当前数组长度
    private int index;
    //可容纳最大长度
    private int MAX_INDEX = 10;

    public MyArray(){
        if (array == null){
            array=new int[MAX_INDEX];
            index = 0;
        }
    }

    public void addList(int num){
        if (num == MAX_INDEX)
        {
            MAX_INDEX = array.length + (array.length >> 1);//10 00001010>>1 00000101 5 ~~array.length*1.5
            array = Arrays.copyOf(array, MAX_INDEX);
        }
        array[index] = num;
        index++;
    }

    public void deleteList(int index2){
        //从当前删除对象往后遍历修改
        for (int i = index2+1; i<index;i++)
        {
            array[i-1] = array[i];
        }
        //最后一个重置为空或0
        array[index] = 0;
        index--;
    }

    public void updateList(int index2, int num){
        array[index2] = num;
    }

    public int getNum(int index2){
        return array[index2];
    }

    public int getLength(){
        return index;
    }

    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0 ; i < index; i++){
            stringBuffer.append(array[i]).append(",");
        }
        stringBuffer.append(array[index]);
        return stringBuffer.toString();
    }
}
