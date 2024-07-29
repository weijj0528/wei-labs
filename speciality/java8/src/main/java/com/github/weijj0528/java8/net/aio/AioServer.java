package com.github.weijj0528.java8.net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Aio 试验
 *
 * @author William.Wei
 */
public class AioServer {

    public static void main(String[] args) throws Exception {
        final AsyncServer asyncServer = new AsyncServer();
        new Thread(asyncServer).start();

        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (asyncServer.sendMsg(scanner.nextLine())) {
        }
    }

    static class AsyncServer implements Runnable {

        private AsynchronousServerSocketChannel channel;
        private CountDownLatch downLatch;
        private List<AsynchronousSocketChannel> channelList = new ArrayList<>();

        public AsyncServer() {
            try {
                channel = AsynchronousServerSocketChannel.open();
                final int port = 12345;
                channel.bind(new InetSocketAddress(port));
                System.out.println("服务启动，端口号：" + port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            downLatch = new CountDownLatch(1);
            channel.accept(this, new AcceptHandler());
            try {
                downLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void countDown() {
            downLatch.countDown();
        }

        public void addChannel(AsynchronousSocketChannel channel) {
            channelList.add(channel);
        }

        public boolean sendMsg(AsynchronousSocketChannel channel, String msg) {
            final byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            final ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();
            channel.write(buffer, buffer, new WriteHandle(channel));
            return true;
        }

        public boolean sendMsg(String msg) {
            if (msg.startsWith("@")) {
                final String[] split = msg.substring(1).split("#");
                if (split.length != 2) {
                    System.err.println("定向发送格式错误，请参考：@address#content");
                } else {
                    try {
                        final AsynchronousSocketChannel socketChannel = channelList.stream().filter(c -> {
                            try {
                                return Objects.equals(split[0], c.getRemoteAddress().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }).findFirst().get();
                        sendMsg(socketChannel, split[1]);
                    } catch (Exception e) {
                        System.err.println("指定客户端未找到：" + split[0]);
                        e.printStackTrace();
                    }
                }
            } else {
                // 广播
                for (AsynchronousSocketChannel channel : channelList) {
                    sendMsg(channel, msg);
                }
            }
            return true;
        }


    }

    static class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServer> {

        @Override
        public void completed(AsynchronousSocketChannel channel, AsyncServer attachment) {
            try {
                System.out.println("客户端连接：" + channel.getRemoteAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 开始下一个连接处理
            attachment.channel.accept(attachment, this);
            // 保持连接
            attachment.addChannel(channel);
            // 注册读数据
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer, buffer, new ReadHandle(channel, new CallbackHandle(attachment)));
        }

        @Override
        public void failed(Throwable exc, AsyncServer attachment) {
            exc.printStackTrace();
            attachment.countDown();
        }
    }

    static class CallbackHandle implements CompletionHandler<Void, String> {

        private AsyncServer asyncServer;

        public CallbackHandle(AsyncServer asyncServer) {
            this.asyncServer = asyncServer;
        }

        @Override
        public void completed(Void result, String attachment) {
            asyncServer.sendMsg(attachment);
        }

        @Override
        public void failed(Throwable exc, String attachment) {
            exc.printStackTrace();
        }
    }
}
