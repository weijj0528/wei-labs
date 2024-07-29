package com.github.weijj0528.java5.thread.base;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 一、线程的创建
 *
 * @author William.Wei
 */
public class ThreadCreateDemo {

    public static void main(String[] args) throws Exception {
        // 继承 Thread 类重写 run方法
        Thread thread1 = new MyThread();
        // 实现 Runnable 接口
        Thread thread2 = new Thread(new MyRunnable());
        // 通过线程池 执行 Runnable接口实现
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("ExecutorService Runnable run");
            }
        });
        // 通过线程池 执行 Callable 接口实现，该种方式可以获取线程执行结果
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt();
            }
        });
        // 启动线程
        thread1.start();
        thread2.start();
        // 获取结果
        Integer integer = future.get();
        System.out.println("Callable result:" + integer);
        // 待线程执行
        System.in.read();
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread run");
        }
    }

    public static class MyRunnable implements Runnable {
        public void run() {
            System.out.println("MyRunnable run");
        }
    }

}
