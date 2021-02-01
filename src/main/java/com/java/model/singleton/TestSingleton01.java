package com.java.model.singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangning
 * @create 2020-12-31 9:02
 * 保证一个类中的实例在JVM中只有一份
 */
class Singleton01 {//该方法在单线程的时候，不会出现问题，不过在多线程的时候，就会出现线程安全问题

	private Singleton01() {
	}

	public static Singleton01 instance;

	public static Singleton01 getInstance() {
		if (instance == null) {
			instance = new Singleton01();
		}
		return instance;
	}
}

class Singleton02 {
	private Singleton02() {
	}

	public static Singleton02 instance;

	public static synchronized Singleton02 getInstance() {
		if (instance == null) {
			instance = new Singleton02();
		}
		return instance;
	}
}

/**
 * Singleton02和Singleton03这两种放肆都可以解决多线程下的安全问题，但是在早期的JVM中Singleton03这种写法效率更高点
 * 但是这两种方法，当有大量线程的时候，可能会造成阻塞
 */

class Singleton03 {
	private Singleton03() {
	}

	public static Singleton03 singleton03;

	public static Singleton03 getInstance() {
		synchronized (Singleton03.class) {
			if (singleton03 == null) {
				singleton03 = new Singleton03();
			}
		}
		return singleton03;
	}
}

class Singleton04 {
	private Singleton04() {
	}

	public static volatile Singleton04 singleton04;

	public static Singleton04 getInstance() {
		if (singleton04 == null) {
			synchronized (Singleton04.class) {
				if (singleton04 == null) {
					singleton04 = new Singleton04();
				}
			}
		}
		return singleton04;
	}
}

public class TestSingleton01 {
	public static void main(String[] args) {

//		new Singleton01();//不能通过这种方式创建Singleton01对象，因为该类的构造方法私有化了
//		doTestSingleton01();
//		doTestSingleton02();
//		doTestSingleton03();
		doTestSingleton04();


	}

	private static void doTestSingleton04() {
		List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new Thread() {
				@Override
				public void run() {
					System.out.println("Singleton02.getInstance() = " + Singleton04.getInstance());
				}
			});
		}
		for (Thread thread : list) {
			thread.start();
		}
	}

	private static void doTestSingleton03() {
		List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(new Thread() {
				@Override
				public void run() {
					System.out.println("Singleton02.getInstance() = " + Singleton03.getInstance());
				}
			});
		}
		for (Thread thread : list) {
			thread.start();
		}
	}

	private static void doTestSingleton02() {
		List<Thread> list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add(new Thread() {
				@Override
				public void run() {
					System.out.println("Singleton02.getInstance() = " + Singleton01.getInstance());
				}
			});
		}
		for (Thread thread : list) {
			thread.start();
		}
	}

	private static void doTestSingleton01() {
		System.out.println("Singleton01.getInstance() = " + Singleton01.getInstance());
		System.out.println("Singleton01.getInstance() = " + Singleton01.getInstance());
		System.out.println("Singleton01.getInstance() = " + Singleton01.getInstance());
		System.out.println("Singleton01.getInstance() = " + Singleton01.getInstance());
	}

}
