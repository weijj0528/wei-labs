package com.github.weijj0528.java5.thread.juc;

import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author William.Wei
 */
public class JucCyclicBarrierDemo {

    public static void main(String[] args) throws IOException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":召唤神龙");
            }
        });

        for (int i = 0; i < 14; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":获得一颗龙珠");
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.in.read();
    }

}
