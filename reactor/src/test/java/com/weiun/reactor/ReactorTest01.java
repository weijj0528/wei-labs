package com.weiun.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * 发送数据
 */
public class ReactorTest01 {


    /**
     * @throws Exception
     */
    @Test
    public void fluxJustTest() throws Exception {
        // 发送一组数据
        System.out.println("just----------------------");
        Flux.just("1", "A", "B")
                .subscribe(System.out::println);
        // 发送一组数据
        System.out.println("fromArray----------------------");
        Flux.fromArray(new String[]{"1", "A", "B"})
                .subscribe(System.out::println);
        // 发送范围内数据
        System.out.println("range----------------------");
        Flux.range(0, 10)
                .subscribe(System.out::println);
        // 发送间隔数据
        System.out.println("interval----------------------");
        Flux.interval(Duration.of(500, ChronoUnit.MILLIS))
                .subscribe(System.out::println);
        Thread.sleep(10000);
    }


    /**
     * @throws Exception
     */
    @Test
    public void fluxEmptyTest() throws Exception {
        // 发送空数据
        System.out.println("empty----------------------");
        Flux.empty()
                .subscribe(System.out::println);
        // 不发送任何数据
        System.out.println("never----------------------");
        Flux.never()
                .subscribe(System.out::println);
        // 发送异常
        System.out.println("error----------------------");
        Flux.error(new Exception("a wo,something is wrong!")).subscribe(System.out::println);
    }

    @Test
    public void fluxGenerateTest() {
        Flux.generate(i -> {
            i.next("AAAAA");
            //i.next("BBBBB");//注意generate中next只能调用1次
            i.complete();
        }).subscribe(System.out::println);

        final Random rnd = new Random();
        Flux.generate(ArrayList::new, (list, item) -> {
            Integer value = rnd.nextInt(100);
            list.add(value);
            item.next(value);
            if (list.size() >= 10) {
                item.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    @Test
    public void fluxCreateTest() {
        Flux.create(i -> {
            i.next("A");
            i.next("B");
            i.complete();
        }).subscribe(System.out::println);

        final Random rnd = new Random();
        Flux.create(item -> {
            for (int i = 0; i < 10; i++) {
                item.next(i);
            }
        }).subscribe(System.out::println);
    }

}
