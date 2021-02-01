package com.java.testthis;

/**
 * this的用法一：大部分时候，普通方法访问其他方法、成员变量时无须使用 this 前缀，
 * 但如果方法里有个局部变量和成员变量同名，但程序又需要在该方法里访问这个被覆盖的成员变量，则必须使用 this 前缀。
 * 提示：当一个类的属性（成员变量）名与访问该属性的方法参数名相同时，则需要使用 this 关键字来访问类中的属性，以区分类的属性和方法中的参数。
 * @author wangning
 * @create 2020-11-30 10:29
 */
class Person {

    private Integer id;

    private String name;

    private Integer age;

    /**
     * 在上述代码中 id、name 和 age 的作用域是 private，因此在类外部无法对它们的值进行设置。
     * 为了解决这个问题，可以为 Person 类添加一个构造方法，然后在构造方法中传递参数进行修改。
     * @param id
     * @param name
     * @param age
     */
    public Person (Integer id, String name, Integer age) {
        /**
         * 在 Person 类的构造方法中使用了 this 关键字对属性 id、name 和 age 赋值，this 表示当前对象。
         * this.name=name语句表示一个赋值语句，等号左边的 this.name 是指当前对象具有的变量 name，等号右边的 name 表示参数传递过来的数值。
         */
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestThis01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class TestThis01 {

    public static void main(String[] args) {

        Person t = new Person(1, "xiao", 18);
        System.out.println(t.toString());

    }
}
