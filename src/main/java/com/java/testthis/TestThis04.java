package com.java.testthis;

/**
 * @author wangning
 * @create 2020-11-30 15:31
 */
class Student {
    private Integer id;
    private String name;
    private String gender;
    private Integer age;

    /**
     * this(...)
     * 重载的构造方法之间调用
     * 目的：减少代码重复
     * 一般从参数少的方法，调用参数多的方法
     * 必须是首行代码
     */
    public Student() {

    }

    public Student(Integer id, String name) {
        this(id, name, null, 0);
        System.out.println("this()必须放在首行");
    }

    public Student(Integer id, String name, String gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

}

public class TestThis04 {
    public static void main(String[] args) {
        Student student = new Student(1, "name","男", 18);
        Student student2 = new Student(1, "name");
    }
}
