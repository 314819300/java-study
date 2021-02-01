package com.java.oop;

/**
 * @author wangning
 * @create 2020-12-12 15:00
 */
interface Male {

}
interface Female {

}
class Boy implements Male {
    private Girl girl;
    public void doWork() {
        System.out.println(" ===== ");
    }
}
class Girl implements Female {

}

public class TestOOP01 {
    public static void main(String[] args) {
        Boy boy = new Boy();
        boy.doWork();

    }
}
