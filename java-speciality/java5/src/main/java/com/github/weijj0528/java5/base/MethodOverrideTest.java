package com.github.weijj0528.java5.base;

/**
 * 方法重写实验
 * 方法重写，子类重写父类方法（方法名称相同，参数列表相同）
 * 注意：重写方法返回类型可以是父类方法返回类型的子类型
 *
 * @author William.Wei
 */
public class MethodOverrideTest extends MethodOverLoadTest {

    public static void main(String[] args) {
        MethodOverrideTest test = new MethodOverrideTest();
        int i = test.test(1, 2);
        System.out.println(i);
        test = test.test(1);
        System.out.println(test);
    }

    /**
     * 方法重写：方法名与参数列表与父类方法相同
     *
     * @param a
     * @param b
     * @return
     */
    @Override
    public int test(int a, int b) {
        System.out.println("子类重写方法");
        return 0;
    }

    /**
     * 重写方法返回类型可以是父类方法返回类型的子类型
     *
     * @param a
     * @return
     */
    @Override
    public MethodOverrideTest test(Integer a) {
        System.out.println("子类重写方法");
        return new MethodOverrideTest();
    }

}
