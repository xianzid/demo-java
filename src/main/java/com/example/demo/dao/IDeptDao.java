package com.example.demo.dao;

import com.example.demo.model.Dept;

import java.util.List;

public interface IDeptDao {
    boolean create(Dept dept) throws Exception;
    List<Dept> findAll();
}
