package com.github.weijj0528.java8.base64;

import java.util.Base64;

/**
 * The type Base 64 test.
 *
 * @author William.Wei
 */
public class Base64Test {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // 在Java 8中，Base64编码已经成为Java类库的标准。
        // Java 8 内置了 Base64 编码的编码器和解码器。
        // Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
        //      基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
        //      URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
        //      MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
        final Base64.Encoder encoder = Base64.getEncoder();
        final Base64.Decoder decoder = Base64.getDecoder();
        final byte[] encode = encoder.encode("123456".getBytes());
        System.out.println(new String(encode));
        System.out.println(new String(decoder.decode(encode)));
    }

}
