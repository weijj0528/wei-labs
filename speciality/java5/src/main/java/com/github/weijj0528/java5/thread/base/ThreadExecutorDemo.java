package com.github.weijj0528.java5.thread.base;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 3、线程池示例
 * Java提供Executors{@link Executors}工具类方便我们创建线程池，Executors提供5种线程池如下：
 * 1：固定数量线程池{@link java.util.concurrent.Executors#newFixedThreadPool(int)}
 * 2：缓存线程池{@link java.util.concurrent.Executors#newCachedThreadPool()}
 * 3：单线程池{@link java.util.concurrent.Executors#newSingleThreadExecutor()}
 * 4：可调度线程池{@link java.util.concurrent.Executors#newScheduledThreadPool(int)}
 * 5：可调度单线程池{@link java.util.concurrent.Executors#newSingleThreadScheduledExecutor()}
 *
 * @author William.Wei
 */
public class ThreadExecutorDemo {

    public static void main(String[] args) throws IOException {
        // 手动创建线程池
        final Object obj = new Object();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                3,
                0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        // 核心参数说明
        // 1：corePoolSize 核心线程数，线程池工作线程数未达到核心线程数量限制时启动新的线程执行当前任务，达到后任务进入队列
        // 2：maximumPoolSize 最大线程数，线程池工作线程数达到核心线程数量限制，且任务队列压满时，若工作线程数小于最大线程数时启动新的额外线程执行当前任务
        // 3：keepAliveTime 线程存活时间，工作线程达到核心线程数量后启动的额外线程在没有任务执行后能存活的时间，在1.8版本后若显式设置核心线程允许超时，则也表示核心线程在没有任务执行后能存活有时候
        // 4：unit 线程存活时间单位，与参数3共同限定线程的存活时间
        // 5：workQueue 工作队列，工作线程数量达到核心线程数量限制后，暂存后续任务
        // 6：threadFactory 线程工厂，需要启动新线程时创建线程的工厂
        // 7：handler 拒绝策略，工作线程达到最大，工作队列排满，后续任务的处理策略，Java提供的有当前线程直接执行，报错、丢弃、丢弃最早的，四种处理策略
        // 任务提交执行分析
        // 1：工作线程数小于核心线程数时启动新的线程执行当前任务
        // 2：任务成功进入排队
        // 2.1：若线程池停止，则移除任务执行拒绝策略
        // 2.2：若没有工作线程时启动新的线程执行排队中的任务
        // 3：未排除成功
        // 3.1：若工作线程数小于最大线程数时启动新的线程执行当前任务
        // 3.2：若工作线程数达到最大线程数时执行拒绝策略
        threadPoolExecutor.submit(new Runnable() {
            public void run() {
                synchronized (obj) {
                    try {
                        // obj.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String name = Thread.currentThread().getName();
                System.out.println("submit Runnable1 in " + name);
            }
        });
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.submit(new Runnable() {
                public void run() {
                    String name = Thread.currentThread().getName();
                    System.out.println(String.format("submit Runnable in %s", name));
                }
            });
        }
        System.in.read();
    }

}
