package com.example.demo.model;

import java.io.Serializable;

public class Student implements Serializable, Comparable<Student> {

    private static final long serialVersionUID = -1632055181705393554L;

    private String studentName;
    private int studentAge;

    public Student() {}

    public Student(String studentName, int studentAge) {
        this.studentName = studentName;
        this.studentAge = studentAge;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentAge=" + studentAge +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (getStudentAge() > o.studentAge)
            return 1;
        else if (getStudentAge() < o.studentAge)
            return -1;
        else
            return 0;
    }
}
