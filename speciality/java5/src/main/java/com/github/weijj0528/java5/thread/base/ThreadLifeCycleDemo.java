package com.github.weijj0528.java5.thread.base;


import java.io.IOException;

/**
 * 2、线程生命周期
 *
 * @author William.Wei
 * @see java.lang.Thread.State
 * <p>
 * 从枚举定义中可以看出Java中线程的状态分为6种。
 * 1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
 * 2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
 * 线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。
 * 该状态的线程位于可运行线程池中，等待被线程调度选中，获取CPU的使用权，此时处于就绪状态（ready）。
 * 就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
 * 3. 阻塞(BLOCKED)：表示线程阻塞于锁。
 * 4. 等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
 * 5. 定时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
 * 6. 终止(TERMINATED)：表示该线程已经执行完毕。
 * 各状态转换
 * NEW --> RUNNABLE
 * 调用线程启动方法（Thread.start()）进行运行
 * *** RUNNABLE <--> BLOCKED
 * 等待获取锁进入 synchronized 块或方法线程进入阻塞状态，获取到锁后回到运行状态
 * *** RUNNABLE <--> WAITING
 * 线程获取锁后执行 Object.wait()/Thread.join()/LockSupport.park()方法后进入等待状态
 * 其他线程获取锁后执行 Object.notify()/Object.notifyAll()/LockSupport.unpark(Thread)方法后或Thread.join()的线程执行结束进入运行状态
 * *** RUNNABLE <--> TIMED_WAITING
 * 线程获取锁后执行 Object.wait(long)/Thread.join(long)/Thread.sleep(long)/LockSupport.parkNanos()/LockSupport.parkUntil()方法后进入定时等待状态
 * 其他线程获取锁后执行 Object.notify()/Object.notifyAll()/LockSupport.unpark(Thread)方法后或Thread.join()的线程执行结束或定时时间到进入运行状态
 * RUNNABLE --> TERMINATED
 * 线程执行完成进行终止
 */
public class ThreadLifeCycleDemo {

    public static void main(String[] args) throws IOException {
        // 实验
        final Object obj = new Object();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // 稍等一会确保主线程获取到锁
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取锁
                synchronized (obj) {
                    // 线程获取到锁
                    try {
                        obj.wait(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 无限等待
                    try {
                        obj.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Runnable run");
                }
            }
        });
        // 新创建的线程处理于初始状态 NEW
        Thread.State state = thread.getState();
        System.out.println("1:" + state);
        thread.start();
        // 线程启动后进行运行状态 RUNNABLE
        state = thread.getState();
        System.out.println("2:" + state);
        synchronized (obj) {
            // 稍等一会确保线程等待获取锁
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 线程获取锁进入阻塞状态 BLOCKED
            state = thread.getState();
            System.out.println("3:" + state);
        }
        // 稍等一会确保线程获取到锁
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程获取到锁执行 obj.wait(long) 进入定时等待状态 TIMED_WAITING
        state = thread.getState();
        System.out.println("4:" + state);
        // 稍等一会确保线程进入无限等待
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程执行 obj.wait() 进入等待状态 WAITING
        state = thread.getState();
        System.out.println("5:" + state);
        // 解除线程等待状态
        synchronized (obj) {
            obj.notify();
        }
        // 稍等一会确保线程运行结束
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 线程执行结束 进入终止状态 TERMINATED
        state = thread.getState();
        System.out.println("6:" + state);
        System.in.read();
    }

}
