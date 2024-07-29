package com.github.weijj0528.java5.base;

/**
 * 方法重载测试
 * 方法名称相同参数列表不同即为方法重载
 * 如方法名称相同参数列表相同返回类型不同则编译出错
 */
public class MethodOverLoadTest {

    public static void main(String[] args) {
        MethodOverLoadTest test = new MethodOverLoadTest();
        int i = test.test(1, 2);
        System.out.println(i);
        i = test.test(Integer.valueOf(1), Integer.valueOf(2));
        System.out.println(i);
    }

    public int test(int a, int b) {
        return 0;
    }

    public int test(int a, Integer b) {
        return 1;
    }

    public int test(Integer a, Integer b) {
        return 1;
    }

    public int test(Integer a, int b) {
        return 1;
    }

//  返回类型不做为方法重载标准
//  public Integer test(Integer a, int b) {
//      return 0;
//  }

    public MethodOverLoadTest test(Integer a) {
        System.out.println("父类方法");
        return new MethodOverrideTest();
    }

}
