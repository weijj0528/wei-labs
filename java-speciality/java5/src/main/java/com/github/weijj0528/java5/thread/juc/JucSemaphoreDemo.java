package com.github.weijj0528.java5.thread.juc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量
 *
 * @author William.Wei
 */
public class JucSemaphoreDemo {

    public static void main(String[] args) throws IOException {
        final Semaphore semaphore = new Semaphore(5);
        List<Thread> threadList = new ArrayList<Thread>(100);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread t = Thread.currentThread();
                        String name = t.getName();
                        semaphore.acquire();
                        System.out.println(name + "-获得许可");
                        TimeUnit.MILLISECONDS.sleep(100);
                        semaphore.release();
                        System.out.println(name + "-释放许可");
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

        System.in.read();

    }

}
