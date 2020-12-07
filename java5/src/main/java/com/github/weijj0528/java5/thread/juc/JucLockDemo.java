package com.github.weijj0528.java5.thread.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 原子量示例
 *
 * @author William.Wei
 */
public class JucLockDemo {


    private static int count1 = 0;
    private static int count2 = 0;


    private static int count3 = 0;
    private static int count4 = 0;
    private static AtomicLong time = new AtomicLong();


    public static void main(String[] args) throws Exception {
        reentrantLockTest();
        reentrantReadWriteLockTest();

        System.in.read();
    }

    private static void reentrantReadWriteLockTest() throws InterruptedException {
        final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        List<Thread> threadList = new ArrayList<Thread>(1001);
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(new Runnable() {
                public void run() {
                    long s = System.currentTimeMillis();
                    try {
                        readLock.lock();
                        long e = System.currentTimeMillis();
                        time.addAndGet(e - s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        readLock.unlock();
                    }
                }
            }));
        }

        threadList.add(new Thread(new Runnable() {
            public void run() {
                long s = System.currentTimeMillis();
                try {
                    writeLock.lock();
                    long e = System.currentTimeMillis();
                    System.out.println("writeLock:" + (e - s));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }
        }));

        for (Thread thread : threadList) {
            thread.start();
        }


        Thread.sleep(3000);

        System.out.println("time:" + time.get());
    }

    private static void reentrantLockTest() throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    count2++;
                    // 加锁
                    try {
                        lock.lock();
                        // 自增
                        count1 += 1;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }).start();
        }

        Thread.sleep(3000);

        System.out.println("count1:" + count1);
        System.out.println("count2:" + count2);
    }

}
