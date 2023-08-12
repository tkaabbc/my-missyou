package com.example.mymissyou.util;

import com.example.mymissyou.bo.PageCounter;

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
}
