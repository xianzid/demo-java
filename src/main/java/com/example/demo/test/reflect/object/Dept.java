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

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", supior=" + supior +
                ", level=" + level +
                '}';
    }
}
