package com.example.demo.test.set;

import java.util.*;

public class ListAndMapAndLinkedTest {
    public static void main(String[] args) {
        long s1 = System.nanoTime();
        ArrayList<String> arrayList = newList();
        long s2 = System.nanoTime();
        System.out.printf("List新增用时：%d,从%d到%d%n" , (s2-s1), s1, s2);
        s1 = System.nanoTime();
        arrayList.remove("Tom");
        s2 = System.nanoTime();
        System.out.printf("List删除单条用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);
        s1 = System.nanoTime();
        arrayList.remove("John");
        arrayList.remove("Gao");
        arrayList.remove("Lily");
        arrayList.remove("Lisa");
        s2 = System.nanoTime();
        System.out.printf("List删除多条用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);

        s1 = System.nanoTime();
        HashMap<Integer, String> map = newMap();
        s2 = System.nanoTime();
        System.out.printf("Map新增用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);
        s1 = System.nanoTime();
        map.remove(1);
        s2 = System.nanoTime();
        System.out.printf("Map删除单条用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);
        s1 = System.nanoTime();
        map.remove(1);
        map.remove(2);
        map.remove(3);
        map.remove(4);
        s2 = System.nanoTime();
        System.out.printf("Map删除多条用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);

        s1 = System.nanoTime();
        LinkedList<String> linkedList = newLinkedList();
        s2 = System.nanoTime();
        System.out.printf("LinkedList新增用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);
        linkedList.remove("Tom");
        s2 = System.nanoTime();
        System.out.printf("LinkedList删除单条用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);
        linkedList.remove("River");
        linkedList.remove("Holy");
        linkedList.remove("Que");
        linkedList.remove("OK");
        s2 = System.nanoTime();
        System.out.printf("LinkedList删除多条用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);

        s1 = System.nanoTime();
        iterableList(arrayList);
        s2 = System.nanoTime();
        System.out.printf("List遍历用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);

        s1 = System.nanoTime();
        iterableLMap(map);
        s2 = System.nanoTime();
        System.out.printf("Map遍历用时：%d ,从%d到%d%n" , (s2-s1), s1, s2);

    }

    /*
    新增ArrayList
     */
    private static ArrayList<String> newList(){
        ArrayList<String> arrayList = new ArrayList<String>();
        for(int i=0;i<20;i++){
            arrayList.add(("name"+i));
        }
        return  arrayList;
    }

    /*
        新增HashMap
    */
    private static HashMap<Integer, String> newMap(){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for(int i=0;i<20;i++){
            map.put((i+1), ("name"+i));
        }
        return map;
    }

    /*
        新增LinkedList
    */
    private static LinkedList<String> newLinkedList(){
        LinkedList<String> linkedList = new LinkedList<String>();
        for(int i=0;i<20;i++){
            linkedList.add(("name"+i));
        }
        return  linkedList;
    }

    /*
    遍历ArrayList
     */
    private static void iterableList(ArrayList<String> arrayList){
        int i = 0;
        for (String str : arrayList){
//            System.out.printf("欢迎第%d位，它的名字是%s%n", ++i, str);
            ++i;
        }
    }

    /*
    遍历HashMap
     */
    private static void iterableLMap(HashMap<Integer, String> maps){
        int i = 0;
        for(Map.Entry<Integer, String> map : maps.entrySet()){
//            System.out.printf("欢迎第%d位，它的名字是%s%n", map.getKey(), map.getValue());
            ++i;
        }

    }

    /*
    遍历HashSet
     */
    private static void testHashSet(){
        HashSet<String> set = new HashSet<String>();
        set.add("d");
        set.add("53f");
        set.add("dsf");
        set.add("iok");
        set.add("vte");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.printf("",iterator.next());
        }
    }

    private static void comparableList(){

    }

    private static void comparableMap(){

    }

    private static void other(){
        Vector vector = new Vector();
    }

}
