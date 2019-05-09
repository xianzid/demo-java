package com.example.demo.test.set;

import com.example.demo.model.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getStudentAge() > o2.getStudentAge())
            return 1;
        if (o1.getStudentAge() < o2.getStudentAge())
            return -1;
        else
            return 0;
    }

    public static void main(String[] args) {
        Student s1 = new Student("C", 18);
        Student s2 = new Student("B", 11);
        Student s3 = new Student("A", 13);
        Student s4 = new Student("E", 16);
        StudentComparator comparator = new StudentComparator();
        System.out.println(comparator.compare(s1,s2));
        System.out.println(comparator.compare(s2,s4));
    }
}
