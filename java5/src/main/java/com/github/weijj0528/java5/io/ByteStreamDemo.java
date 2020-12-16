package com.github.weijj0528.java5.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 字节流试验
 */
public class ByteStreamDemo {

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
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(name.getBytes());
        fos.flush();
        fos.close();
        // 读取内容
        FileInputStream fis = new FileInputStream(file);
        final byte[] b = new byte[1024];
        fis.read(b);
        System.out.println(new String(b));

        file.delete();
    }

}
