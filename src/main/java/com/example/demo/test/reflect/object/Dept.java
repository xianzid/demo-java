package com.example.demo.test.reflect.object;

public class Dept {
    public final static String MAIN_ROOT = "ROOT";
    public int id;
    public String deptName;
    public String deptCode;
    public int supior;
    public int level;

    public Dept(){}
    public Dept(String deptName, int level){
        this.deptName = deptName;
        this.level = level;
    }
    public Dept(String deptName, int level, Dept superDept){
        this.deptName = deptName;
        this.level = level;
        this.supior = superDept.id;
    }
}
