package com.weiun.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * 多数据源
 */
public class ReactorTest03 {

    /**
     * merge将把多个Flux"按元素实际产生的顺序"合并，
     * mergeSequential按多个Flux"被订阅的顺序"来合并
     * concat连接多个Flux
     */
    @Test
    public void mergeTest() {
        Flux.merge(Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(5),
                Flux.interval(Duration.of(600, ChronoUnit.MILLIS), Duration.of(500, ChronoUnit.MILLIS)).take(5))
                .toStream().forEach(System.out::println);

        System.out.println("-----------------------------");

        Flux.mergeSequential(Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(5),
                Flux.interval(Duration.of(600, ChronoUnit.MILLIS), Duration.of(500, ChronoUnit.MILLIS)).take(5))
                .toStream().forEach(System.out::println);

        System.out.println("-----------------------------");
        Flux.concat(Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(5),
                Flux.interval(Duration.of(600, ChronoUnit.MILLIS), Duration.of(500, ChronoUnit.MILLIS)).take(5))
                .toStream().forEach(System.out::println);
    }

    /**
     * 该操作会将所有流中的最新产生的元素合并成一个新的元素，作为返回结果流中的元素。
     * 只要其中任何一个流中产生了新的元素，合并操作就会被执行一次
     */
    @Test
    public void combineLatestTest() {
        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.of(10000, ChronoUnit.MILLIS)).take(3),
                Flux.just("A", "B"))
                .toStream().forEach(System.out::println);

        System.out.println("------------------");

        Flux.combineLatest(
                Arrays::toString,
                Flux.just(0, 1),
                Flux.just("A", "B"))
                .toStream().forEach(System.out::println);

        System.out.println("------------------");

        Flux.combineLatest(
                Arrays::toString,
                Flux.interval(Duration.of(1000, ChronoUnit.MILLIS)).take(2),
                Flux.interval(Duration.of(10000, ChronoUnit.MILLIS)).take(2))
                .toStream().forEach(System.out::println);
    }

    /**
     * 多个Flux，只取第1个Flux的元素。
     */
    @Test
    public void firstTest() {
        Flux.first(Flux.fromArray(new String[]{"A", "B"}),
                Flux.just(1, 2, 3))
                .subscribe(System.out::println);
    }

}
