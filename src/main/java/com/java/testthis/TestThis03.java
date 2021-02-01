package com.java.testthis;

/**
 * this()访问构造方法
 *
 * @author wangning
 * @create 2020-11-30 11:57
 */
public class TestThis03 {
    /**
     * java中静态方法中为什么不能使用this、super和直接调用非静态方法
     * 这个要从java的内存机制去分析，首先当你New 一个对象的时候，并不是先在堆中为对象开辟内存空间，而是先将类中的静态方法
     * （带有static修饰的静态函数）的代码加载到一个叫做方法区的地方，然后再在堆内存中创建对象。所以说静态方法会随着类的加载而被加载。
     * 当你new一个对象时，该对象存在于对内存中，this关键字一般指该对象，但是如果没有new对象，而是通过类名调用该类的静态方法也可以。
     *
     * 程序最终都是在内存中执行，变量只有在内存中占有一席之地时才会被访问，类的静态成员（静态变量和静态方法）属于类本身，在类加载的
     * 时候就会分配内存，可以通过类名直接去访问，非静态成员（非静态变量和非静态方法）属于类的对象，所以只有在类的对象创建（实例化）的
     * 时候才会分配内存，然后通过类的对象去访问。
     * 在一个类的静态成员中去访问非静态成员之所以会出错是因为在类的非静态成员不存在的时候静态成员就已经存在了，访问一个内存中不存在的东西当然会出错。
     *
     * 注意：static在java中是不可以写实局部变量的。
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(this);
        TestThis03 test = new TestThis03();
        test.test();
    }

    public void test() {
        //特殊引用，引用当前对象地址，不可以在静态方法中直接使用
        System.out.println(this.getClass().getName());
    }

}
