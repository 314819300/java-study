package com.java.generic;

/**
 * @author wangning
 * @create 2020-12-12 16:14
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型的上下界问题
 */
public class TestGeneric02 {
    static void doPoint(List<? extends Object> list) {
        //这种方法只能移除内容，不能添加内容
        System.out.println(list);
    }

    public static void main(String[] args) {
        ArrayList<? extends Object> list1 = new ArrayList<Integer>();
        ArrayList<? super Integer> list2 = new ArrayList<Object>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        list3.add(100);
        list3.add(101);
        list3.add(102);
        doPoint(list3);
    }
}
