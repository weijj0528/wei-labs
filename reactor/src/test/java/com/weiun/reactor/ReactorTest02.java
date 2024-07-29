package com.weiun.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CountDownLatch;

/**
 * 数据处理
 */
public class ReactorTest02 {

    /**
     * 缓冲
     *
     * @throws InterruptedException
     */
    @Test
    public void fluxBufferTest() throws InterruptedException {
        Flux.range(0, 10).buffer(3).subscribe(System.out::println);

        System.out.println("--------------");

        Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
                .bufferTimeout(2, Duration.of(2, ChronoUnit.SECONDS))
                .subscribe(System.out::println);

        //防止程序过早退出，放一个CountDownLatch拦住
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }

    /**
     * 过滤
     */
    @Test
    public void fluxFilterTest() {
        Flux.range(0, 10).filter(c -> c % 2 == 0).subscribe(System.out::println);
    }

    /**
     * 各组元素，按位组合
     * 这里有一个木桶原则，即 元素最少的"组"，决定了最后输出的"组"个数
     */
    @Test
    public void fluxZipTest() {
        Flux.just("A", "B").zipWith(Flux.just("1", "2", "3")).subscribe(System.out::println);
    }

    /**
     * take与takeLast很好理解，就是前n个或后n个
     * takeWhile 是先判断条件是否成立，然后再决定是否取元素（换言之，如果一开始条件不成立，就直接终止了）
     * takeUntil 是先取元素，直到遇到条件成立，才停下
     * takeUntilOther 则是先取元素，直到别一个Flux序列产生元素
     */
    @Test
    public void fluxTakeTest() {
        Flux.range(1, 10).take(3).subscribe(System.out::println);
        System.out.println("--------------");
        Flux.range(1, 10).takeLast(3).subscribe(System.out::println);
        System.out.println("--------------");
        Flux.range(1, 10).takeWhile(c -> c > 1 && c < 5).subscribe(System.out::println);
        System.out.println("--------------");
        Flux.range(1, 10).takeUntil(c -> c > 1 && c < 5).subscribe(System.out::println);
        System.out.println("--------------");
        Flux.range(1, 4).takeUntilOther(Flux.never()).subscribe(System.out::println);
    }

    /**
     * reduce相当于把1到10累加求和
     * reduceWith则是先指定一个起始值，然后在这个起始值基础上再累加
     */
    @Test
    public void reduceTest() {
        Flux.range(1, 10).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 10).reduceWith(() -> 10, (x, y) -> x + y).subscribe(System.out::println);
    }

    /**
     * map相当于把一种类型的元素序列，转换成另一种类型
     */
    @Test
    public void mapTest() {
        Flux.just('A', 'B', 'C').map(a -> (int) (a)).subscribe(System.out::println);
    }

}
