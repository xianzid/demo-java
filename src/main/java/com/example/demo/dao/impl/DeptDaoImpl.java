package com.example.demo.dao.impl;

import com.example.demo.dao.IDeptDao;
import com.example.demo.model.Dept;

import java.util.List;

public class DeptDaoImpl implements IDeptDao {

    /**
     * 老的写法，返回接口IDeptDao，实例化对象是DeptDaoImpl
     * 或者通过spring.ioc在用到IDeptDao的地方注入DeptDaoImpl实例对象
     */
    public IDeptDao getInstance(){
        return new DeptDaoImpl();
    }

    @Override
    public boolean create(Dept dept) throws Exception{
        System.out.println("创建部门");
        return true;
    }

    @Override
    public List<Dept> findAll() {
        System.out.println("查询所有部门信息");
        return null;
    }
}
