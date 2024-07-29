package com.github.weijj0528.java5.net.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BioClientHandle implements Runnable {

    private BioService service;
    private String name;

    private Socket socket;

    private PrintWriter writer;

    private boolean flag = true;

    public BioClientHandle(BioService service, String name, Socket socket) {
        this.service = service;
        this.name = name;
        this.socket = socket;
        try {
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(name + "已连接");
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            scanner.useDelimiter("\n");
            while (flag) {
                String x = scanner.nextLine();
                if ("Close".equals(x)) {
                    shutDown();
                    break;
                }
                // List 获取客户端列表
                if ("List".equals(x)) {
                    String list = service.list();
                    sendMsg(list);
                    continue;
                }
                // Cast:{content} 广播
                if (x.startsWith("Cast")) {
                    service.cast(x.replaceAll("Cast:", ""));
                    continue;
                }
                // To {name}:{content} 私聊
                if (x.startsWith("To")) {
                    String[] split = x.replaceAll("To ", "").split(":");
                    if (split.length != 2) {
                        sendMsg("消息格式不正确……");
                    } else {
                        service.sendMsg(name, split[0], split[1]);
                    }
                } else {
                    sendMsg("Echo:" + x);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutDown() {
        try {
            service.close(name);
            socket.close();
            System.out.println(name + "已断开");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        writer.println(msg);
        writer.flush();
    }
}
