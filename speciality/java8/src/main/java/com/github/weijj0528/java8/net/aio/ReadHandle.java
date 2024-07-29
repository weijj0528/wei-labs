package com.github.weijj0528.java8.net.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class ReadHandle implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel channel;
    private CompletionHandler<Void, String> handler;

    ReadHandle(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    ReadHandle(AsynchronousSocketChannel channel, CompletionHandler<Void, String> handler) {
        this.channel = channel;
        this.handler = handler;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        final byte[] bytes = new byte[attachment.remaining()];
        attachment.get(bytes);
        final String message = new String(bytes);
        try {
            System.out.println(String.format("[%s]:", channel.getRemoteAddress()) + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 注册下一次读
        attachment.flip();
        attachment.clear();
        channel.read(attachment, attachment, this);
        // 读取到数据后的处理
        if (handler != null) {
            handler.completed(null, message);
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }
}