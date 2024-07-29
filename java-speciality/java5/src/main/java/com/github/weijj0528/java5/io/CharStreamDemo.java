package com.github.weijj0528.java5.io;

import java.io.*;

/**
 * 字节流试验
 */
public class CharStreamDemo {

    public static void main(String[] args) throws Exception {
        // 基本介质流，从文件、Byte数组、String中读取数据
        // FileInputStream
        // ByteArrayInputStream
        // StringBufferInputStream

        // 包装流
        // 

        // 文件的读写
        final String name = "D:\\test.txt";

        File file = new File(name);
        if (!file.exists()) {
            file.createNewFile();
        }

        // 写入内容
        FileWriter fw = new FileWriter(file);
        fw.write(name);
        fw.flush();
        fw.close();
        // 读取内容
        FileReader fis = new FileReader(file);
        final char[] b = new char[1024];
        fis.read(b);
        System.out.println(new String(b));

        file.delete();
    }

}
