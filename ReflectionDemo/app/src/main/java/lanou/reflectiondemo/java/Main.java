package lanou.reflectiondemo.java;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by ZhangRui on 16/12/5.
 */

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("老宋");
        person.setAge(18);
        person.sex = "m";
        System.out.println(person.getAge());
        // 通过反射 拿到真是(private)的年龄
        // 使用反射的第一步
        Class<? extends Person> aClass // 类类型
                = person.getClass();
        // 通过类类型 来拿到类名
        System.out.println(aClass.getSimpleName());
        // 不加declared 的方法.  只能拿到有权限访问的
        // 属性, 方法
        // 加了之后, 就会无视掉权限操作符, 什么都能访问了
        // 基本上 在使用反射的时候, 都会使用declared ***
        Field[] fields = aClass.getFields();
        Field[] declared = aClass.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        for (Field field : declared) {
            System.out.println(field.getName());
            if ("age".equals(field.getName())) {
                // 找到了age这个属性
                try {
                    // 声明这个成员变量可以访问的
                    // 否则, 是没办法读取到 private的成员变量值得
                    field.setAccessible(true);
                    int age = field.getInt(person);
                    System.out.println("真实年龄:" + age);
                    field.setInt(person, 18);
                    System.out.println("修改后:" + person.getAge());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            // 通过反射调用方法
            System.out.println("------------");
            // 获得类里的所有方法
//            Method[] declaredMethods = aClass.getDeclaredMethods();
//            for (Method declaredMethod : declaredMethods) {
//
            try {
                // 通过反射拿到一个方法
                // 第一个参数是 方法名
                // 之后的参数是 该方法的参数列表 的类类型
                // 写的顺序, 按照 参数列表的声明顺序
                Method setName = aClass.getDeclaredMethod("setName", String.class);
                // 设置该方法可以被访问
                setName.setAccessible(true);
                try {
                    setName.invoke(person,"小宋");// 调用方法
                    System.out.println("改名之后:"+person.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        }

    }
}
