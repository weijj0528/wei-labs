package com.github.weijj0528.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Lambda test 001.
 *
 * @author William.Wei
 */
public class LambdaTest001 {

    public static void main(String[] args) {
        // Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
        // 也就是说一个 Lambda 表太式就是一个函数（方法），可以转化为一个匿名内部类，类型则由接收的方法签名确定
        // 这个类型必须是一个接口，有且仅有一个抽象方法，JDK8接口中的静态方法和默认方法，都不算是抽象方法。
        // 这样的接口称之为函数式接口，JDK8中提供了 @FunctionalInterface 注解来进行这项检查
        // 该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。
        // 加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。
        MathOperation addition = (Integer::sum);
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> {
            return a / b;
        };

        System.out.println("1 + 2 = " + addition.operation(1, 2));
        System.out.println("1 - 2 = " + subtraction.operation(1, 2));
        System.out.println("1 * 2 = " + multiplication.operation(1, 2));
        System.out.println("1 / 2 = " + division.operation(1, 2));

        // 方法引用
        // 方法引用通过方法的名字来指向一个方法。
        // 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
        // 方法引用使用一对冒号 ::
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.forEach(System.out::println);

        // 构造器引用：它的语法是Class::new，或者更一般的Class< T >::new
        // 静态方法引用：它的语法是Class::static_method
        // 特定类的任意对象的方法引用：它的语法是Class::method
        // 特定对象的方法引用：它的语法是instance::method
        list.forEach(list::indexOf);
    }

    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);

        default int addition(int a, int b) {
            return a + b;
        }
    }

    // @FunctionalInterface
    abstract class AbsMathOperation implements MathOperation {

    }

}
