package com.example.mymissyou.util;

import com.example.mymissyou.bo.PageCounter;

import java.util.Calendar;
import java.util.Date;

public class CommonUtil {

    public static PageCounter convertToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;

        PageCounter pageCounter = PageCounter.builder()
                .page(pageNum)
                .count(count)
                .build();
        System.out.println("pageCounter.getPage()");
        System.out.println(pageCounter.getPage());
        System.out.println(pageNum);
        return pageCounter;
    }

    public static Calendar addSomeSeconds(Calendar calendar, int seconds) {
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }

    public static Boolean isInTimeLine(Date date, Date start, Date end) {
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if (time > startTime && time < endTime) {
            return true;
        }
        return false;
    }

    //period 单位：秒
    public static Boolean isOutOfDate(Date startTime, Long period) {
        Long now = Calendar.getInstance().getTimeInMillis();
        Long startTimeStamp = startTime.getTime();
        Long periodMillSecond = period * 1000;
        if (now > (startTimeStamp + periodMillSecond)) {
            return true;
        }
        return false;
    }

    public static Boolean isOutOfDate(Date expiredTime) {
        Long now = Calendar.getInstance().getTimeInMillis();
        Long expiredTimeStamp = expiredTime.getTime();
        if(now > expiredTimeStamp){
            return true;
        }
        return false;
    }
}
