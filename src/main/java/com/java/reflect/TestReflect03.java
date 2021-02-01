package com.java.reflect;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangning
 * @create 2020-12-29 9:59
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface TestLog {
	String value() default "";
}

class Person {
	private int id;
	private String name;
}

class User extends Person {
	public static int num = 0;
	@TestLog(value = "测试注解")
	private int age;
	private String desc;

	public User() {

	}
	public User(int age) {
		this.age = age;
	}

	public void work(int aa) {
		System.out.println("work..." + aa);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "User{" +
				"age=" + age +
				", desc='" + desc + '\'' +
				"} " + super.toString();
	}
}

public class TestReflect03 {
	public static void main(String[] args) throws Exception {
		Class<?> aClass = Class.forName("com.java.reflect.User");

		//1.类对象的运行时类的Class对象
		System.out.println("aClass.getClass() = " + aClass.getClasses());

		//2.获取类名

		//2.1获取类全名
		System.out.println("aClass.getName() = " + aClass.getName());
		//2.2获取包名
		System.out.println("aClass.getPackage().getName() = " + aClass.getPackage().getName());
		//2.2获取类名，不包含包名
		System.out.println("aClass.getSimpleName() = " + aClass.getSimpleName());

		//3.获取内部类：返回类定义的公共的内部类,以及从父类、父接口那里继承来的内部类,主要是获取内部类
		System.out.println("aClass.getClasses() = " + aClass.getClasses().length);

		//4.获取内部类：返回类中定义的公共、私有、保护的内部类,获取该类的内部类，除去父类
		System.out.println("aClass.getDeclaredClasses() = " + aClass.getDeclaredClasses());

		//5.通过反射获取属性名与属性的值
		System.out.println("\n");
		System.out.println("----------5.通过反射获取属性名与属性的值----------");
		//getFields()
		//获得可见的成员变量，包含从父类继承的变量
		//getDeclaredFields()
		//获得本类中定义的所有成员变量，
		//包括私有变量
		//getField(变量名)
		//getDeclaredField(变量名)
		//获得一个成员变量
		User user = new User();
		System.out.println("user.getDesc()00 = " + user.getDesc());
		//5.1通过反射获取user中的desc属性
		Field desc = aClass.getDeclaredField("desc");
		//5.2设置属性可访问.使私有的属性能够被访问
		desc.setAccessible(true);
		//5.3修改user对象中desc属性的值
		desc.set(user, //目标对象
				"123456");//修改User对象中的属性的值
		System.out.println("user.getDesc() = " + user.getDesc());
		System.out.println("user.getAge() = " + user.getAge());

		System.out.println("aClass.getFields() = " + aClass.getFields());
		Field[] fields = aClass.getDeclaredFields();
		String nameVlues = "";
		if (fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				//得到属性
				Field field = fields[i];
				//打开私有访问
				field.setAccessible(true);
				//获取属性
				String name = field.getName();
				//获取属性值
				Object value = field.get(user);
				//一个个赋值
				nameVlues += field.getName() + ":" + value + ",";

			}
			//获取最后一个逗号的位置
			int lastIndex = nameVlues.lastIndexOf(",");
			//不要最后一个逗号","
			String result = nameVlues.substring(0, lastIndex);
			System.out.println("result" + result);

		}

		//6.反射调用构造方法
		System.out.println("\n");
		System.out.println("----------6.反射调用构造方法----------");
		//getConstructors()
		//获得可见的构造方法
		//getDeclaredConstructors()
		//获得所有构造方法，包括私有
		//getConstructor(参数类型列表)
		//getDeclaredConstructor(参数类型列表)
		//获得一个构造方法
		//参数类型列表：String.class, int.class
		Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
		System.out.println("declaredConstructors.length = " + declaredConstructors.length);
		for (int i = 0; i < declaredConstructors.length; i++) {
			Constructor<?> declaredConstructor = declaredConstructors[i];
			System.out.println("declaredConstructor.getName() = " + declaredConstructor.getName());
		}

		//7.获取方法的定义信息
		System.out.println("\n");
		System.out.println("----------7.获取方法的定义信息----------");
		Method[] declaredMethods = aClass.getDeclaredMethods();
		for (int i = 0; i < declaredMethods.length; i++) {
			Method declaredMethod = declaredMethods[i];
			System.out.println("declaredMethod.getName() = " + declaredMethod.getName());
		}

		Method work = aClass.getDeclaredMethod("work",int.class);
		System.out.println("work.getTypeParameters() = " + work.getTypeParameters());
		Object invoke = work.invoke(user, 29);

		//8.反射创建对象
		System.out.println("\n");
		System.out.println("----------8.反射创建对象----------");
		//8.1通过无参构造函数创建对象
		Object o = aClass.newInstance();
		System.out.println("o.getClass().getName() = " + o.getClass().getName());
		//8.2通过有参构造
		Constructor constructor = aClass.getConstructor(int.class);
		User o1 = (User)constructor.newInstance(28);
		System.out.println("o1.getAge() = " + o1.getAge());

		//9.通过反射获取注解及注解的值
		System.out.println("\n");
		System.out.println("----------9.通过反射获取注解----------");
		Class<?> aClass1 = Class.forName("com.java.reflect.TestLog");
		//9.1获取注解信息
		Annotation[] annotations = aClass1.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println("annotation = " + annotation);
		}
		//9.2获取注解
		Target annotation = (Target) aClass1.getAnnotation(Target.class);
		ElementType[] annoValue = annotation.value();
		for (ElementType elementType: annoValue) {
			System.out.println("elementType = " + elementType);
		}
		//9.3获取属性上的注解的值
		Field name = aClass.getDeclaredField("age");
		TestLog annotation1 = (TestLog) name.getAnnotation(TestLog.class);
		String value = annotation1.value();
		System.out.println("value = " + value);
		System.out.println("annotation1.annotationType() = " + annotation1.annotationType());


		//10.使用反射修改静态变量的值
		System.out.println("\n");
		System.out.println("----------10.使用反射修改静态变量的值----------");
		User user1 = new User();
		Field num = aClass.getDeclaredField("num");
		num.setAccessible(true);
		num.set(null, 27);//如果是静态变量，第一个值给null
		System.out.println("user1 = " + User.num);


	}
}
