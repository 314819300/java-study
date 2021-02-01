package com.java.testthis;

/**
 * this 关键字最大的作用就是让类中一个方法，访问该类里的另一个方法或实例变量。
 * @author wangning
 * @create 2020-11-30 11:17
 */

/**
 * 假设定义了一个 Dog 类，这个 Dog 对象的 run( ) 方法需要调用它的 jump( ) 方法，Dog 类的代码如下所示：
 */
class Dog {
    public void jump() {
        System.out.println("正在执行jump方法");
    }
    public void run() {
        //第一种
//        Dog dog = new Dog();
//        dog.jump();
        //第二种
//        this.jump();//this.jump()等同于new Dog();然后调用Dog对象中的方法
        //第三种
        jump();
        System.out.println("正在执行run方法");
    }

    public static void test() {
        //静态成员访问非静态的时候，以下两种就是错误的语法，静态成员不能直接访问非静态成员,但是可以通过 Dog 对象访问其内部的成员
//        jump();
//        this.jump();
        Dog dog = new Dog();
        dog.jump();
    }
}

/**
 * 1）在 run( ) 方法中调用 jump( ) 方法时是否一定需要一个 Dog 对象？
 * 答案是肯定的，因为没有使用 static 修饰的成员变量和方法都必须使用对象来调用。
 *
 * 2）是否一定需要重新创建一个 Dog 对象？
 * 不一定，因为当程序调用 run( ) 方法时，一定会提供一个 Dog 对象，这样就可以直接使用这个已经存在的 Dog 对象，
 * 而无须重新创建新的 Dog 对象了。因此需要在 run() 方法中获得调用该方法的对象，通过 this 关键字就可以满足这个要求。
 * this 可以代表任何对象，当 this 出现在某个方法体中时，它所代表的对象是不确定的，但它的类型是确定的，它所代表的只能
 * 是当前类的实例。只有当这个方法被调用时，它所代表的对象才被确定下来，谁在调用这个方法，this 就代表谁。
 *
 * 从第一种 Dog 类定义来看，在 Dog 对象的 run( ) 方法内重新创建了一个新的 Dog 对象，并调用它的 jump( ) 方法，
 * 这意味着一个 Dog 对象的 run( ) 方法需要依赖于另一个 Dog 对象的 jump( ) 方法，这不符合逻辑。
 *
 * 第二种 Dog 类定义是当一个 Dog 对象调用 run( ) 方法时，run( ) 方法需要依赖它自己的 jump( ) 方法，
 * 与第一种定义类的方法相比，更符合实际情形。
 *
 * 在现实世界里，对象的一个方法依赖于另一个方法的情形很常见，例如，吃饭方法依赖于拿筷子方法，写程序方法依赖于敲键盘方法。
 * 这种依赖都是同一个对象两个方法之间的依赖。因此，Java 允许对象的一个成员直接调用另一个成员，可以省略 this 前缀。
 * 也就是说，将上面的 run( ) 方法改为第三种也完全正确。
 *
 * 大部分时候，一个方法访问该类中定义的其他方法、成员变量时加不加 this 前缀的效果是完全一样的。
 *
 * 注意：对于 static 修饰的方法而言，可以使用类来直接调用该方法，如果在 static 修饰的方法中使用 this 关键字，则这个关键字就
 * 无法指向合适的对象。所以，static 修饰的方法中不能使用 this 引用。并且 Java 语法规定，静态成员不能直接访问非静态成员。
 *
 * 省略 this 前缀只是一种假象，虽然程序员省略了调用 jump() 方法之前的 this，但实际上这个 this 依然是存在的。
 */
public class TestThis02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.run();
    }
}
