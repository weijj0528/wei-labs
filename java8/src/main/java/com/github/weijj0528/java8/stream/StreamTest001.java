package com.github.weijj0528.java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Stream test 001.
 *
 * @author William.Wei
 */
public class StreamTest001 {

    public static void main(String[] args) {
        // Stream<T> 的生成
        final Stream<Integer> stream1 = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Stream<Integer> stream2 = Arrays.stream(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Stream<Integer> stream3 = list.stream();
        Random random = new Random();

        // 遍历
        stream1.forEach(System.out::println);
        // 跳过与限定
        stream2.skip(2).limit(3).forEach(System.out::println);
        // 过滤
        stream3.filter(integer -> integer > 5).forEach(System.out::println);
        // 排序
        random.ints(10).sorted().forEach(System.out::println);
        // 去重
        random.ints(10).distinct().forEach(System.out::println);
        // 转换
        Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).map(integer -> integer * integer).forEach(System.out::println);
        // 展开（内部元素转换为流）
        Stream.of(new Integer[]{9, 8, 7}, new Integer[]{6, 5, 4}).flatMap(Stream::of).forEach(System.out::println);
        // 统计
        final double average = random.ints(10).summaryStatistics().getAverage();
        System.out.println(average);
        // 归约操作
        // 归约为集合（Collection,List,Set）
        final ArrayList<Integer> collect = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.toCollection(ArrayList::new));
        collect.forEach(System.out::println);
        // 归类
        final HashMap<Boolean, List<Integer>> map = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.groupingBy(o -> o > 5, HashMap::new, Collectors.toList()));
        // 转Map 参数说明：key匹配，value匹配，key冲突处理，Map提供
        final HashMap<Integer, String> hashMap = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.toMap(k -> k, String::valueOf, (o1, o2) -> o1, HashMap::new));
        // 简单统计
        final Double averagingDouble = Stream.of(0, 1, 2, 3, 4, 5).collect(Collectors.averagingDouble(Double::valueOf));
        System.out.println(averagingDouble);
        // 拼接
        final String joining = Stream.of(0, 1, 2, 3, 4, 5).map(String::valueOf).collect(Collectors.joining(",", "(", ")"));
        System.out.println(joining);

    }

}
