package com.github.weijj0528.java5.thread.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程同步
 * 简单生产者与消费者实验
 *
 * @author William.Wei
 */
public class ThreadSyncDemo {

    private volatile static boolean open = true;

    public static void main(String[] args) throws Exception {
        final Shop shop = new Shop(10);
        // 生产者
        new Thread(new Runnable() {
            public void run() {
                while (open) {
                    shop.add();
                }
                System.out.println("生产结束");
            }
        }).start();
        // 消费
        new Thread(new Runnable() {
            public void run() {
                while (open) {
                    String s = shop.get();
                    System.out.println("消费" + s);
                    try {
                        // 消费慢点
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("消费结束");
            }
        }).start();

        System.in.read();
        shop.close();
        open = false;
        Thread.sleep(1000);
    }

    public static class Shop {

        // 库存
        private final List<String> store;
        // 最大库存
        private final int maxStore;
        // 商品序号
        private final AtomicInteger index;

        public Shop(int maxStore) {
            index = new AtomicInteger(0);
            store = new ArrayList<String>(maxStore);
            this.maxStore = maxStore;
        }

        // 生产
        public synchronized void add() {
            if (store.size() == maxStore) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int get = index.addAndGet(1);
            store.add("P" + get);
            System.out.println("生产P" + get);
            notifyAll();
        }

        // 消费
        public synchronized String get() {
            if (store.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String product = store.remove(0);
            notifyAll();
            return product;
        }

        public synchronized void close() {
            notifyAll();
        }

    }

}
