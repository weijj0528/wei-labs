package com.github.weijj0528.java5.io;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * 文件系统试验
 *
 * @author William.Wei
 */
public class FileDemo {

    public static void main(String[] args) throws Exception {
        File file = new File("c:");
        // 文件&目录
        if (file.isFile()) {
            System.out.println(file.getName() + ":" + "是文件");
        }
        if (file.isDirectory()) {
            System.out.println(file.getPath() + ":" + "是目录");
        }

        // 随机读写
        final String name = "d:\\test.txt";
        file = new File(name);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.writeChars("Hello world!");
        raf.close();
        raf = new RandomAccessFile(file, "rw");
        raf.writeChars("F");
        raf.close();

        raf = new RandomAccessFile(file, "rw");
        System.out.println(raf.readLine());
        raf.close();

        file.delete();
    }

}
