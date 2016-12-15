package lanou.reflectiondemo.java;

/**
 * Created by ZhangRui on 16/12/5.
 */

public class Person {
    private String name;
    private int age;
    public String sex;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age - 3;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
