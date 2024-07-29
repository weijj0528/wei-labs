package com.weiun.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 异常处理
 */
public class ReactorTest04 {

    /**
     * 这里subscribe第2个参数，指定了System.err::println ，即错误消息，输出到异常控制台上。
     */
    @Test
    public void subscribeTest1() {
        Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IndexOutOfBoundsException("下标越界啦！")))
                .subscribe(System.out::println, System.err::println);
    }

    /**
     * 遇到错误时，用其它指定值返回
     */
    @Test
    public void subscribeTest2() {
        Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IndexOutOfBoundsException("下标越界啦！")))
                .onErrorReturn("X")
                .subscribe(System.out::println, System.err::println);
    }

    /**
     * 可以根据异常的类型，有选择性的处理返回值。
     */
    @Test
    public void subscribeTest3() {
        Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IndexOutOfBoundsException("下标越界啦！")))
                .onErrorResume(e -> {
                    if (e instanceof IndexOutOfBoundsException) {
                        return Flux.just("X", "Y", "Z");
                    } else {
                        return Mono.empty();
                    }
                })
                .subscribe(System.out::println, System.err::println);
    }


    /**
     * 遇到异常，就重试。
     */
    @Test
    public void subscribeTest4() {
        Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IndexOutOfBoundsException("下标越界啦！")))
                .retry(1)
                .subscribe(System.out::println, System.err::println);
    }

}
