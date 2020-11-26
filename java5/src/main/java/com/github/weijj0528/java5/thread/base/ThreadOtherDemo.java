package com.github.weijj0528.java5.thread.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程其他
 * 线程的命名
 * 线程的分组
 * 线程的类型
 * 线程的优先级
 *
 * @author William.Wei
 */
public class ThreadOtherDemo {

    private volatile static boolean open = true;

    public static void main(String[] args) throws Exception {
        // 线程分组
        final ThreadGroup group = new ThreadGroup("My");
        List<AtomicInteger> timeList = new ArrayList<AtomicInteger>();
        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            final AtomicInteger time = new AtomicInteger(0);
            timeList.add(time);
            Thread thread = new Thread(group, new Runnable() {
                public void run() {
                    String name = Thread.currentThread().getName();
                    while (open) {
                        int t = time.addAndGet(1);
                        System.out.println(name + " run " + t);
                        synchronized (group) {
                            try {
                                Thread.sleep(100);
                                group.wait(100);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            // 线程命名
            thread.setName("thread" + i);
            // 默认为普通线程，可设置为守护线程，随启动线程的线程终止而终止，这里的启动线程的线程就是Main线程
            thread.setDaemon(true);
            // 线程的优先级，默认为5，越大优先级越高
            thread.setPriority(i + 1);
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            // 启动线程
            thread.start();
        }
        System.in.read();

        // 开关关闭
        open = false;
        // 等待线程结束
        for (Thread thread : threadList) {
            thread.join();
        }

        for (int i = 0; i < timeList.size(); i++) {
            AtomicInteger time = timeList.get(i);
            System.out.println(String.format("time%d:%d", (i + 1), time.get()));
        }
    }

}
