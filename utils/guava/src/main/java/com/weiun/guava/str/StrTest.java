package com.weiun.guava.str;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.Arrays;
import java.util.List;

/**
 * @author William
 * @Date 2019/3/8
 * @Description 字符串工具测试
 */
public class StrTest {


    public static void main(String[] args) {
        // Joiner
        String join = Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, null, 6));
        System.out.println(join);
        join = Joiner.on(",").useForNull("5").join(Arrays.asList(1, 2, 3, 4, null, 6));
        System.out.println(join);
        StringBuilder sb = new StringBuilder();
        Joiner.on(",").useForNull("5").appendTo(sb, Arrays.asList(1, 2, 3, 4, null, 6));
        System.out.println(sb.toString());

        //Splitter
        List<String> strings = Splitter.on(",").splitToList("1,2,   4,3  ,5,");
        System.out.println(strings);
        strings = Splitter.on(",").omitEmptyStrings().splitToList("1,2,   4,3  ,5,");
        System.out.println(strings);
        strings = Splitter.on(",").trimResults().splitToList("1,2,   4,3  ,5,");
        System.out.println(strings);
        strings = Splitter.on(",").trimResults().omitEmptyStrings().splitToList("1,2,   4 3 ,3  ,5,");
        System.out.println(strings);

        String s = CharMatcher.digit().retainFrom("   Wil3li1am2 ");
        System.out.println(s);
    }

}
