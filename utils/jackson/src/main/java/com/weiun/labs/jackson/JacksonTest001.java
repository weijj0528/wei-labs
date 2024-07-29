package com.weiun.labs.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weiun.labs.jackson.pojo.User1;

import java.io.File;

/**
 * The type Jackson test 001.
 * 基础使用
 *
 * @author William.Wei
 */
public class JacksonTest001 {

    public static void main(String[] args) throws Exception {
        final User1 user = User1.createInstance();
        System.out.println(user);
        // ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 注册类型模块以支持Java8时间日期类型，需要引入com.fasterxml.jackson.datatype:jackson-datatype-jsr310包
        // 还有其他类型模块，可按需引入：https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype
        mapper.registerModule(new JavaTimeModule());
        // 也可以自动搜索
        mapper.findAndRegisterModules();
        // 序列化为字符串
        final String jsonStr = mapper.writeValueAsString(user);
        System.out.println(jsonStr);
        // 序列化为字节
        final byte[] jsonBytes = mapper.writeValueAsBytes(user);
        System.out.println(new String(jsonBytes));
        // 序列化到文件
        final File file = new File("./user.json");
        mapper.writeValue(file, user);
        // 从文件中读取
        final User1 value1 = mapper.readValue(file, User1.class);
        System.out.println(value1);
        // 从字节中读取
        final User1 value2 = mapper.readValue(jsonBytes, User1.class);
        System.out.println(value2);
        // 从字符串中读取
        final User1 value3 = mapper.readValue(jsonStr, User1.class);
        System.out.println(value3);
    }

}
