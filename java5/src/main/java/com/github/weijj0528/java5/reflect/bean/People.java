package com.github.weijj0528.java5.reflect.bean;

/**
 * @author William
 * @Date 2019/3/20
 * @Description 人
 */
public class People<T> implements Action {

    public static final String PUBLIC_PEOPLE = "PUBLIC_PEOPLE";

    private static final String PRIVATE_PEOPLE = "PRIVATE_PEOPLE";

    public String a;

    private String b;


    public People() {
    }

    public void sya(String content) {
        System.out.println("From People:" + content);
    }

    public static void testPeopleStaticMetodh() {
    }

    private static void testPeoplePriveteStaticMetodh() {
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    private void testPeoplePriveteMethod() {
    }
}
