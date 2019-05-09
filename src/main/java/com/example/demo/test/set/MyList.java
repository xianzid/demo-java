package com.example.demo.test.set;


/**
 * 自己写的列表
 */
public interface MyList {
    /*
    新增
     */
    public  void addList(int num);

    /*
    单条删除
     */
    public  void deleteList(int index2);

    /*
    修改
     */
    public  void updateList(int index2,int num);

    /*
    查找
     */
    public  int getNum(int index2);

    /*
    获取长度
     */
    public  int getLength();
}
