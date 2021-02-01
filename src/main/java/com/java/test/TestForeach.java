package com.java.test;

import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangning
 * @create 2021-01-05 15:11
 */
public class TestForeach {
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6};
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.forEach(item -> {
			System.out.println("item = " + item);
		});
//		a.forEach(item -> {
//
//		});
	}
}
