package com.example.demo.test.reflect.model;

import java.math.BigDecimal;
import java.util.Date;

public class Dept {
    public final static String MAIN_ROOT = "ROOT";
    private int id;
    private String deptName = "部门名称";
    private String deptCode = "部门编码";
    private int supior;
    private int level;
    private Date createTime = new Date();
    private BigDecimal account = new BigDecimal(00.00);
    private boolean isSuper = false;

    public Dept(){}
    public Dept(String deptName, int level){
        this.deptName = deptName;
        this.level = level;
    }
    public Dept(String deptName, int level, Dept superDept) throws IllegalArgumentException{
        this.deptName = deptName;
        this.level = level;
        this.supior = superDept.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getSupior() {
        return supior;
    }

    public void setSupior(int supior) {
        this.supior = supior;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean aSuper) {
        isSuper = aSuper;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", supior=" + supior +
                ", level=" + level +
                ", createTime=" + createTime +
                ", account=" + account +
                ", isSuper=" + isSuper +
                '}';
    }
}
