package com.weiun.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 测试
 */
public class ReactorTest06 {


    /**
     * 单元测试
     * Flux先生成1，2这两个元素，然后抛了个错误，但马上用onErrorReturn处理了异常，所以最终应该是期待1，2，3，complete这样的序列
     */
    @Test
    public void stepTest() {
        StepVerifier.create(Flux.just(1, 2)
                .concatWith(Mono.error(new IndexOutOfBoundsException("test")))
                .onErrorReturn(3))
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .verifyComplete();
    }

    /**
     * 模拟时间流逝
     * Flux.interval这类延时操作，如果延时较大，比如几个小时之类的，要真实模拟的话，效率很低，
     * StepVerifier提供了withVirtualTime方法，来模拟加快时间的流逝
     */
    @Test
    public void stepTest2() {
        StepVerifier.withVirtualTime(() -> Flux.interval(Duration.of(10, ChronoUnit.MINUTES),
                Duration.of(5, ChronoUnit.SECONDS))
                .take(2))
                .expectSubscription()
                .expectNoEvent(Duration.of(10, ChronoUnit.MINUTES))
                .thenAwait(Duration.of(5, ChronoUnit.SECONDS))
                .expectNext(0L)
                .thenAwait(Duration.of(5, ChronoUnit.SECONDS))
                .expectNext(1L)
                .verifyComplete();
    }

    /**
     * 记录日志
     */
    @Test
    public void publisherTest1() {
        Flux.just(1, 0)
                .map(c -> 1 / c)
                .log("MY-TEST")
                .subscribe(System.out::println);
    }

    /**
     * 可以在一些怀疑的地方，加上checkpoint检查
     */
    @Test
    public void publisherTest2() {
        Flux.just(1, 0)
                .map(c -> 1 / c)
                .checkpoint("AAA")
                .subscribe(System.out::println);
    }

}
