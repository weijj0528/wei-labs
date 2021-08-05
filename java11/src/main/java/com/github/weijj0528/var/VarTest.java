package com.github.weijj0528.var;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类型推断
 *
 * @author William.Wei
 */
public class VarTest {

    public static void main(String[] args) {
        var x = "HelloWorld!";
        System.out.println(x);

        List<String> list = Stream.of("1", "2", "3").collect(Collectors.toList());
        for (var s : list) {
            System.out.println(s);
        }
    }

}
