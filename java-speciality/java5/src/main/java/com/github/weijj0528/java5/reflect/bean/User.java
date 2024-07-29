package com.github.weijj0528.java5.reflect.bean;

/**
 * @author William
 * @Date 2019/3/20
 * @Description 用户
 */
@Deprecated
public class User extends People<User> {

    public static int PUBLIC_USER = 1;

    private static String PRIVATE_USER = "0";

    public String name;

    private int age;

    public User() {
    }

    private User(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void testUserStaticMethod() {
    }

    private static void testUserPriveteStaticMethod() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void testUserPrivateMethod() {
    }
}
