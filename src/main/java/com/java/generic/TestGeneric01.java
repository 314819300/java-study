package com.java.generic;

/**
 * 泛型的练习
 * @author wangning
 * @create 2020-12-12 15:13
 */

/**
 * 何为泛型？
 * 1)JDK1.5以后推出的一种新的参数化类型
 * 2)通常可以理解为一种编译时的类型，在运行时无效
 * 3)类似于生活中的标签(例如商品的标签)
 *
 * 为何要使用泛型？
 * 1)约束类中属性类型，方法参数类型，方法返回值类型
 * 2)提高运行时的性能
 * List l = new ArrayList();
 *  l.add(100);
 *  Integer o = (Integer)l.get(0);
 *
 * 1)
 * 2)
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类
 * @param <T>
 */
class Container<T> {
    private Object array[];
    public Container(int cap) {
        array = new Object[cap];
    }
    public void add(T t) {

    }
    public T get() {
        return (T)array[0];
    }
}

class ContainerUtil {
    public static  <E>List<E> sort(List<E> list) {
        return null;
    }
    public static <T> T get(List<T> list) {
        if(list!=null&&list.size()>0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}

public class TestGeneric01 {
    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>();
        l.add("C");
        l.add("B");
        ContainerUtil.sort(l);
        String s = ContainerUtil.get(l);
        System.out.println("s = " + s);

    }
}
/**
 * 泛型 (Generic type 或者 generics) 是对Java语言的类型系统的一种扩展，以支持创建可以按照类型进行参数化的类。
 * 可以把类型参数看做是使用参数化类型时指定的类型的一个占位符，就像方法的形式参数是运行时传递的值的占位符一样。
 *
 * 泛型的好处
 * 1、类型安全。
 * 泛型的主要目标是提高java程序的类型安全。通过知道使用泛型定义的变量的类型限制，编译器可以在一个高得多的程度上
 * 验证类型假设。没有泛型，这些假设就只存在于程序员的头脑中
 * 2、消除强制类型转换。
 * 泛型的一个附带好处是，消除源代码中的许多强制类型转换。这使得代码更加可读，并且减少出错机会
 * 3、潜在的性能收益。
 * 泛型为较大的优化带来可能。在泛型的初始化实现中，编译器将强制类型转换(没有泛型的话，程序员会指定这些强制类型转换)
 * 插入生成的字节码中。，但是更多类信息可用于编译器这一事实，为未来版本的JVM优化带来了可能。由于泛型的实现方式，
 * 支持
 * 泛型几乎不需要JVM或者类文件更改。所有的工作都在编译器中完成，编译器生成类似于没有泛型（和强制类型转换）时所写的代码
 * 只是更能确保类型安全而已。
 * Java 语言引入泛型的好处是安全简单，泛型的好处是在编译的时候检查类型安全，并且所有的强制转换都是自动和隐式的，提高代码
 * 的重用率
 * 泛型在使用中的一些规则和限制：
 * 1、泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
 * 2、同一种单行可以对应多个版本（因为参数类型时不确定的），不同版本的泛型类实例是不兼容的。
 * 3、泛型的类型参数可以有多个。
 * 4、泛型的参数类型可以使用extends语句，例如，习惯性成为"有界类型"
 * 5、泛型的参数类型还可以是通配符类型，例如Class<?> classType = Class.forName(Java.lang.String);
 *
 *
 *
 *
 *
 * Java 泛型的参数只可以代表类，不能代表个别对象。
 * 由于Java泛型的类型参数之实际类型在编译时会被消除，所以无法在运行时得知其类型参数的类型，而且无法直接使用基本值类型作为泛型类型参数。
 * Java编译程序在编译泛型时会自动加入类型转换的编码，故运行速度不会因为使用泛型而加快。
 *
 * 由于运行时会消除泛型的对象实例类型信息等缺陷经常被人诟病，Java及JVM的开发方面也尝试解决这个问题，
 * 例如Java通过在生成字节码时添加类型推导辅助信息，从而可以通过反射接口获得部分泛型信息。通过改进泛型在JVM的实现，
 * 使其支持基本值类型泛型和直接获得泛型信息等。
 *
 * Java允许对个别泛型的类型参数进行约束，包括以下两种形式[2]（假设T是泛型的类型参数，C是一般类、泛类，或是泛型的类型参数）：
 *
 * T实现接口I。
 * T是C，或继承自C。
 */