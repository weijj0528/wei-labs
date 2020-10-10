package com.github.weijj0528.java5.net.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {

    public static void main(String[] args) throws Exception {
        final Socket socket = new Socket("localhost", 8080);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Scanner socketScanner = new Scanner(socket.getInputStream());
                    socketScanner.useDelimiter("\n");
                    while (true) {
                        String s = socketScanner.nextLine();
                        System.out.println(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        while (true) {
            String s = scanner.nextLine();
            if (s == null || s.length() == 0) {
                continue;
            }
            printWriter.println(s);
            printWriter.flush();
            if ("Close".equals(s)) {
                break;
            }
        }
    }
}
