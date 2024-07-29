package com.github.weijj0528.java8.net.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * AIO 客户端
 *
 * @author William.Wei
 */
public class AioClient1 {

    public static void main(String[] args) throws Exception {
        final AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        final ConnectHandle connectHandle = new ConnectHandle(channel);
        final CountDownLatch latch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                channel.connect(new InetSocketAddress("127.0.0.1", 12345), connectHandle, connectHandle);
                try {
                    latch.await();
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (connectHandle.sendMsg(scanner.nextLine())) {
        }
    }

    static class ConnectHandle implements CompletionHandler<Void, ConnectHandle> {

        private AsynchronousSocketChannel channel;
        private final WriteHandle writeHandle;

        public ConnectHandle(AsynchronousSocketChannel channel) {
            this.channel = channel;
            writeHandle = new WriteHandle(channel);
        }

        @Override
        public void completed(Void result, ConnectHandle attachment) {
            System.out.println("已连接");
            // 注册读取
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer, buffer, new ReadHandle(channel));
        }

        @Override
        public void failed(Throwable exc, ConnectHandle attachment) {
            exc.printStackTrace();
        }

        public boolean sendMsg(String msg) {
            final byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            final ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer, buffer, writeHandle);
            return true;
        }

    }
}
