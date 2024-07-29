package com.github.weijj0528.java5.net.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ReactorService {


    private static boolean flag = true;

    public static void main(String[] args) throws IOException {
        final Map<String, Handler> clientMap = new ConcurrentHashMap<String, Handler>();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //绑定监听8899端口
//        serverSocketChannel.bind(new InetSocketAddress(8080));
        Selector selector = Selector.open();
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        key.attach(new Acceptor(selector, serverSocketChannel, clientMap));
        new Thread(new Runnable() {
            public void run() {
                Scanner scanner = new Scanner(System.in);
                scanner.useDelimiter("\n");
                while (true) {
                    String s = scanner.nextLine();
                    for (Map.Entry<String, Handler> entry : clientMap.entrySet()) {
                        entry.getValue().send(s);
                    }
                    if ("Close".equals(s)) {
                        flag = false;
                        break;
                    }
                }
            }
        }).start();
        // 单线程即可管理多个连接
        while (flag) {
            try {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    dispatch(iterator.next());
                    iterator.remove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void dispatch(SelectionKey k) {
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }


    static class Acceptor implements Runnable {

        private final Selector selector;

        private final ServerSocketChannel serverSocketChannel;
        private Map<String, Handler> clientMap;

        private AtomicInteger count = new AtomicInteger(0);

        public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel, Map<String, Handler> clientMap) {
            this.selector = selector;
            this.serverSocketChannel = serverSocketChannel;
            this.clientMap = clientMap;
        }

        public void run() {
            try {
                SocketChannel channel = serverSocketChannel.accept();
                int i = count.incrementAndGet();
                if (channel != null) {
                    new Handler(i, selector, channel, clientMap);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class Handler implements Runnable {

        private final String name;
        private Map<String, Handler> clientMap;
        private final SocketChannel channel;
        private final SelectionKey key;

        ByteBuffer input = ByteBuffer.allocate(1024);
        ByteBuffer output = ByteBuffer.allocate(1024);

        static final int READING = 0, SENDING = 1;
        int state = READING;

        public Handler(int i, Selector selector, SocketChannel channel, Map<String, Handler> clientMap) throws Exception {
            this.name = "Handler" + i;
            this.clientMap = clientMap;
            this.channel = channel;
            this.channel.configureBlocking(false);
            key = this.channel.register(selector, SelectionKey.OP_READ);
            key.attach(this);
            key.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
            clientMap.put(name, this);
        }

        public void run() {
            try {
                read();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        boolean inputIsComplete() {
            return input.hasRemaining();
        }

        boolean outputIsComplete() {
            return output.hasRemaining();
        }

        void process() throws IOException {
            input.flip();
            Charset charset = Charset.forName("UTF-8");
            String receiveMsg = String.valueOf(charset.decode(input).array());
            System.out.println(channel + ":" + receiveMsg);
            if ("List".equals(receiveMsg)) {
                StringBuilder sb = new StringBuilder();
                sb.append("已连接客户端：");
                for (Map.Entry<String, Handler> entry : clientMap.entrySet()) {
                    sb.append("\n");
                    sb.append(entry.getKey());
                }
                receiveMsg = sb.toString();
            }
            send(receiveMsg);
        }

        void read() throws IOException {
            try {
                input.clear();
                channel.read(input);
                if (inputIsComplete()) {
                    process();
                    // state = SENDING;
                    // Normally also do first write now
                    //第三步,接收write就绪事件
                    key.channel();
                }
            } catch (IOException e) {
                channel.close();
                clientMap.remove(name);
                e.printStackTrace();
            }
        }

        void send(String msg) {
            try {
                output.clear();
                output.put(("Senderkey:" + channel + "\n" + msg).getBytes());
                output.flip();
                channel.write(output);
                //write完就结束了, 关闭select key
                if (outputIsComplete()) {
                    state = READING;
                    key.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
