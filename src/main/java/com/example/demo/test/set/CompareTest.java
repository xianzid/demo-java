package com.example.demo.test.set;

import com.example.demo.model.Student;

import java.util.*;

public class CompareTest {
    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testArrayListComparator();
    }

    private static void testArrayList(){
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("A", 43));
        students.add(new Student("B", 22));
        students.add(new Student("C", 31));
        students.add(new Student("D", 27));
        students.add(new Student("E", 18));
        long time1 = System.nanoTime();
        System.out.printf("[testArrayList]NunCompare student List:%n%s%n Time is: %d%n", students.toString(), time1);
        Collections.sort(students);
        long time2 = System.nanoTime();
        System.out.printf("[testArrayList]After Compare student List:%n%s%n Time is: %d%n", students.toString(), time2);//24230514  20958514
        System.out.printf("[testArrayList]Compare use time: %d%n", (time2-time1));
    }

    private static void testLinkedList(){
        LinkedList<Student> students = new LinkedList<Student>();
        students.add(new Student("A", 55));
        students.add(new Student("B", 43));
        students.add(new Student("C", 3));
        students.add(new Student("D", 27));
        students.add(new Student("E", 16));
        long time1 = System.nanoTime();
        System.out.printf("[testLinkedList]NunCompare student List:%n%s%n Time is: %d%n", students.toString(), time1);
        Collections.sort(students);
        long time2 = System.nanoTime();
        System.out.printf("[testLinkedList]After Compare student List:%n%s%n Time is: %d%n", students.toString(), time2);//20647346 367344
        System.out.printf("[testLinkedList]Compare use time: %d%n", (time2-time1));
    }

    private static void testArrayListComparator(){
        ArrayList students = new ArrayList();
        students.add(new Student("C", 18));
        students.add(new Student("B", 11));
        students.add(new Student("A", 13));
        students.add(new Student("E", 16));
        students.add(new Student("D", 14));
        StudentComparator comparator = new StudentComparator();
        long time1 = System.nanoTime();
        System.out.printf("[testArrayListComparator]NunCompare student List:%n%s%n Time is: %d%n", students.toString(), time1);
        Collections.sort(students, comparator);
        long time2 = System.nanoTime();
        System.out.printf("[testArrayListComparator]After Compare student List:%n%s%n Time is: %d%n", students.toString(), time2);//40874509 384375
        System.out.printf("[testArrayListComparator]Compare use time: %d%n", (time2-time1));
    }

    private static void testArrayListComparator2(){
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("A", 43));
        students.add(new Student("B", 22));
        students.add(new Student("C", 31));
        students.add(new Student("D", 27));
        students.add(new Student("E", 18));
        long time1 = System.nanoTime();
        System.out.printf("[testArrayList]NunCompare student List:%n%s%n Time is: %d%n", students.toString(), time1);
        Collections.sort(students, (o1, o2) -> o1.getStudentAge() - o2.getStudentAge());
        /*
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getStudentAge() - o2.getStudentAge();
            }
        });
         */
        long time2 = System.nanoTime();
        System.out.printf("[testArrayList]After Compare student List:%n%s%n Time is: %d%n", students.toString(), time2);//24230514  20958514
        System.out.printf("[testArrayList]Compare use time: %d%n", (time2-time1));
    }
}
