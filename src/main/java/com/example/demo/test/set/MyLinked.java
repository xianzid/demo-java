package com.example.demo.test.set;

/**
 * 自己写的链表列表
 */
public class MyLinked implements MyList{
    /*
    初始Node对象
     */
    private Node start;

    /*
    当前Node对象
     */
    private Node index;

    /*
    当前长度
     */
    private int count;

    /**
     * 内部类
     * num 当前Node的值
     * next 下个Node对象
     */
    public class Node{
        public int  num;
        public Node next;
    }

    public MyLinked(){
        start = new Node();
        index = start;
        count = 0;
    }

    @Override
    public void addList(int num) {
        //生成新Node对象，内容为num
        Node newNode = new Node();
        newNode.num = num;

        //把上个Node对象指向自己
        index.next = newNode;
        //当前Node对象更新成自己
        index = newNode;
        count++;
    }

    @Override
    public void deleteList(int index2) {
        if (index2 == 0 || index2>count){
            System.out.println("异常值");
        }
        //获取前个Node对象，从start对象开始找
        Node tempNode = start;
        for (int i =0;i<index2;i++){
            tempNode = tempNode.next;
        }
        //前个对象的next指向下下个Node对象
        tempNode.next = tempNode.next.next;

        count--;
    }

    @Override
    public void updateList(int index2, int num) {
        if (index2 == 0 || index2>count){
            System.out.println("异常值");
        }
        //获取当前Node对象
        Node tempNode = start;
        for (int i =0;i<=index2;i++){
            tempNode = tempNode.next;
        }

        tempNode.num = num;
    }

    @Override
    public int getNum(int index2) {
        if (index2 == 0 || index2>count){
            System.out.println("异常值");
        }
        Node tempNode = start;
        for(int i=0;i<=index2;i++){
            tempNode = tempNode.next;
        }
        return tempNode.num;
    }

    @Override
    public int getLength() {
        return count;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Node tempNode = start.next;
        for (int i=0;i<count-1;i++){
            stringBuffer.append(tempNode.num).append(",");
            tempNode = tempNode.next;
        }
        stringBuffer.append(tempNode.num);
        return stringBuffer.toString();
    }

}
