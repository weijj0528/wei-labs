package com.weiun.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * 线程调度
 */
public class ReactorTest05 {


    /**
     * 5.1 Schedulers.immediate() - 使用当前线程
     * 5.2 Schedulers.elastic() - 使用线程池
     * 5.3 Schedulers.newElastic("test1") - 使用(新)线程池(可以指定名称，更方便调试)
     * 5.4 Schedulers.single() - 单个线程
     * 5.5 Schedulers.newSingle("test2") - (新)单个线程(可以指定名称，更方便调试)
     * 5.6 Schedulers.parallel() - 使用并行处理的线程池（取决于CPU核数)
     * 5.7 Schedulers.newParallel("test3")  - 使用并行处理的线程池（取决于CPU核数，可以指定名称，方便调试)
     * 5.8 Schedulers.fromExecutorService(Executors.newScheduledThreadPool(5)) - 使用Executor（这个最灵活)
     */
    @Test
    public void schedulesTest() {
        Flux.fromArray(new String[]{"A", "B", "C", "D"})
                .publishOn(Schedulers.newSingle("TEST-SINGLE", true))
                .map(x -> String.format("[%s]: %s", Thread.currentThread().getName(), x))
                .toStream()
                .forEach(System.out::println);
    }

}
