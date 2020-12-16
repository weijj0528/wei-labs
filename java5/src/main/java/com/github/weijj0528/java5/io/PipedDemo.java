package com.github.weijj0528.java5.io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道试验
 */
public class PipedDemo {

    public static void main(String[] args) throws Exception {
        final PipedInputStream pis = new PipedInputStream();
        final PipedOutputStream pos = new PipedOutputStream();
        pos.connect(pis);
        // 向管道中写数据
        new Thread(new Runnable() {
            public void run() {
                try {
                    int i = 0;
                    while (true) {
                        synchronized (pis) {
                            if (pis.available() <= 0) {
                                i++;
                                pos.write(String.valueOf(i).getBytes());

                            }
                            pis.notify();
                        }
                        Thread.sleep(300);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 从管道中读数据
        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        synchronized (pis) {
                            if (pis.available() > 0) {
                                final byte[] b = new byte[128];
                                pis.read(b);
                                System.out.println(new String(b));
                                pis.wait();
                            }
                        }
                        Thread.sleep(300);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.in.read();
    }

}
