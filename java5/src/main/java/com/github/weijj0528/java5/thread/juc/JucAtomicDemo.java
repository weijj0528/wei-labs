package com.github.weijj0528.java5.thread.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子量示例
 *
 * @author William.Wei
 */
public class JucAtomicDemo {


    private static int count1 = 0;

    private static AtomicInteger count2 = new AtomicInteger(0);


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    // 自增
                    count1 += 1;
                    count2.incrementAndGet();
                }
            }).start();
        }

        Thread.sleep(3000);

        System.out.println("count1:" + count1);
        System.out.println("count2:" + count2.get());

        System.in.read();
    }

}
