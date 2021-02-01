package com.java.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangning
 * @create 2020-12-19 16:01
 *
 * Jackson 的使用
 *
 */
public class TestJson03 {
	public static void main(String[] args) throws IOException {
		//1.对象转json
		ObjectMapper objectMapper = new ObjectMapper();
		Student student = new Student();
		student.setName("小明");
		student.setId(999);
		try {
			String jsonString = objectMapper.writeValueAsString(student);
			System.out.println("jsonString = " + jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		//2.json转对象
		String jsonString1 = "{\"id\":999,\"name\":\"小li\"}";
		try {
			Student student1 = objectMapper.readValue(jsonString1, Student.class);
			System.out.println("jsonString1 = " + student1.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		/**
		 * 3.list to JSON
		 */
		Student student1 = new Student();
		student1.setId(1);
		student1.setName("xiaoming");
		Student student2 = new Student();
		student2.setId(1);
		student2.setName("xiaoming");

		List<Student> studentList = new ArrayList<>();
		studentList.add(student1);
		studentList.add(student2);

		String listJson = objectMapper.writeValueAsString(studentList);
		System.out.println("listJson = " + listJson);

		/**
		 * listJSON转化为list的两种方式
		 * 第一种
		 * 这种写法会出现警告，如果公司要求比较严格，那样就可以使用第二种
		 * 为什么警告呢，因为他只能帮助你解析list集合的类型，但是不能帮你解决其中的泛型的类型
		 * List<>尖括号中写什么都是正确的,不会进行检查，此处的警告只是为了告诉你这个
		 */

		List<Student> list = objectMapper.readValue(listJson, studentList.getClass());
		System.out.println("list = " + list);
		/**
		 * 第二种
		 */
		Student[] students = objectMapper.readValue(listJson, Student[].class);
		System.out.println(Arrays.asList(students));
	}
}
