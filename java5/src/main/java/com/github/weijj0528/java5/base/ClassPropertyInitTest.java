package com.github.weijj0528.java5.base;

/**
 * 类属性初始化实验
 * 类属性初始化按如下顺序进行
 * 父类静态变量
 * 子类静态变量
 * 父类静态代码
 * 父类构造方法
 * 子类静态代码
 * 子类构造方法
 * <p>
 * 先属性后方法（块代码与构造方法），先父类后子类
 *
 * @author William.Wei
 */
public class ClassPropertyInitTest {

    public static void main(String[] args) {
        C c = new C();
        System.out.println(c);
    }


    public static class A {

        {
            System.out.println("f:start");
        }

        private static B fb = new B("f");

        {
            System.out.println("f:end");
        }

        public A() {
            System.out.println("fa");
        }
    }

    public static class B {

        public B(String flag) {
            System.out.println(flag + "b");
        }
    }

    public static class C extends A {

        {
            System.out.println("s:start");
        }

        private static B sb = new B("s");

        {
            System.out.println("s:end");
        }

        public C() {
            System.out.println("sc");
        }
    }

}
