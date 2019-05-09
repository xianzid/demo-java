package com.example.demo.tools;

import java.io.Closeable;

public class CloseUtils {

    public static void closeAuto(AutoCloseable... closeables){
        for (AutoCloseable obj : closeables){
            if (obj != null){
                try {
                    obj.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
