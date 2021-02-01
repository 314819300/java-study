package com.java.teststatic;

/**
 * @author wangning
 * @create 2021-01-05 15:26
 * 1.静态方法不能直接调用非静态方法，但是非静态方法可以直接调用静态方法
 * 2.static方法就是没有this的方法。在static方法内部不能调用非静态方法，反过来是可以的。
 * 而且可以在没有创建任何对象的前提下，仅仅通过类本身来调用static方法。这实际上正是static方法的主要用途。
 * 方便在没有创建对象的情况下来进行调用（方法/变量）。
 * 很显然，被static关键字修饰的方法或者变量不需要依赖于对象来进行访问，只要类被加载了，就可以通过类名去进行访问。
 */
public class TestStatic01 {
	/**
	 * 静态内部类
	 */
	static class TestInner {
		static int num = 10;
		public void work() {
			sleep();
		}
		public void sleep() {}
	}

	public static void main(String[] args) {
		System.out.println(TestInner.num);
		doEat();
		TestStatic01 testStatic01 = new TestStatic01();
		testStatic01.doWork();
	}

	public void doWork() {
		doEat();
	}
	public static  void doEat() {

	}
}
