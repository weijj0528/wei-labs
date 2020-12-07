package com.github.weijj0528.java5.thread.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 闭锁
 *
 * @author William.Wei
 */
public class JucCountDownLatchDemo {

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        List<Thread> threadList = new ArrayList<Thread>((int) countDownLatch.getCount());
        for (int i = 0; i < threadList.size(); i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread t = Thread.currentThread();
                        String name = t.getName();
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.println(name);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.setName("Thread:" + i);
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        countDownLatch.await();

        System.out.println("全部结束");

    }

}
