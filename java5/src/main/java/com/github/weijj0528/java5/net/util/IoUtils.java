package com.github.weijj0528.java5.net.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IoUtils {


    public static String readInputStreamAsString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String s = bufferedReader.readLine();
        while (s != null) {
            sb.append(s);
            s = bufferedReader.readLine();
        }
        bufferedReader.close();
        return sb.toString();
    }
}
