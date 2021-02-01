package com.java.reflect;

/**
 * @author wangning
 * @create 2020-12-23 21:40
 * 通过反射创建对象
 */
class Container {
	private Container() {
		System.out.println("Container()");
	}
}
public class TestReflect02 {
	static Object newInstance(Class<?> cls) {
		return null;
	}
	public static void main(String[] args) throws Exception {
		//1.加载类
		Class<?> c1 = Class.forName("com.java.reflect.Container");
		Container instance = (Container) newInstance(c1);
	}
}
