package com.java.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangning
 * @create 2020-12-19 15:48
 *
 * Fastjson是一个Java语言编写的高性能的JSON处理器,由阿里巴巴公司开发。
 * FastJson在复杂类型的Bean转换Json上会出现一些问题，可能会出现引用的类型，导致Json转换出错，需要制定引用。
 * FastJson采用独创的算法，将parse的速度提升到极致，超过所有json库。
 * 将对象转换成格式化的json
 * JSON.toJSONString(obj, true);
 * 将对象转换成非格式化的json
 * JSON.toJSONString(obj, false);
 * toString（）会调用toJSONString（），因此可以近似认为两者一样
 */
public class TestJson02 {
	public static void main(String[] args) {
		Student student = new Student();
		student.setId(3);
		student.setName("333");
		String jsonString = JSON.toJSONString(student);
		Object o = JSON.toJSON(student);
		System.out.println("o = " + o.toString());
		System.out.println("jsonString = " + jsonString);

		//使用JSONObject对象构建
		JSONObject object = new JSONObject();
		//string
		object.put("string","string");
		//int
		object.put("int",2);
		//boolean
		object.put("boolean",true);
		//array
		List<Integer> integers = Arrays.asList(1,2,3);
		object.put("list",integers);
		//null
		object.put("null",null);
		System.out.println(object.toJSONString());

		//从字符串解析JSON对象
		JSONObject obj = JSON.parseObject("{\"runoob\":\"菜鸟教程\"}");
		//从字符串解析JSON数组
		JSONArray arr = JSON.parseArray("[\"菜鸟教程\",\"RUNOOB\"]\n");
		//将JSON对象转化为字符串
		String objStr = JSON.toJSONString(obj);
		//将JSON数组转化为字符串
		String arrStr = JSON.toJSONString(arr);




	}
}
