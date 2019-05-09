package com.example.demo.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerUtils {
    private static DateFormat dDate = new SimpleDateFormat("dd");
    private static DateFormat sDate = new SimpleDateFormat("ss");

    public static String getSecond(){
        return sDate.format(new Date());
    }

    public static Long getIntervalSecond(String beginSecond){
        String endSecond = getSecond();
        return Long.valueOf(endSecond) - Long.valueOf(beginSecond);
    }
}