package com.weiun.labs.jackson;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weiun.labs.jackson.pojo.User1;
import com.weiun.labs.jackson.pojo.User2;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Jackson test 001.
 * 对XML的支持
 *
 * @author William.Wei
 */
public class JacksonTest005 {

    public static void main(String[] args) throws Exception {
        final User2 user = User2.createInstance();
        System.out.println(user);
        // XmlMapper 需要对一些特殊的类型进行正确的格式化，否则会有异常，如 LocalDateTime需要格式化为 yyyy-MM-dd HH:mm:ss
        XmlMapper mapper = new XmlMapper();
        // 注册类型模块以支持Java8时间日期类型，需要引入com.fasterxml.jackson.datatype:jackson-datatype-jsr310包
        // 还有其他类型模块，可按需引入：https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype
        mapper.registerModule(new JavaTimeModule());
        // 也可以自动搜索
        mapper.findAndRegisterModules();
        // 特性
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 序列化为字符串
        String jsonStr = mapper.writeValueAsString(user);
        System.out.println(jsonStr);
        // 序列化为字节
        final byte[] jsonBytes = mapper.writeValueAsBytes(user);
        System.out.println(new String(jsonBytes));
        // 序列化到文件
        final File file = new File("./user.xml");
        mapper.writeValue(file, user);
        // 从文件中读取
        final User2 value1 = mapper.readValue(file, User2.class);
        System.out.println(value1);
        // 从字节中读取
        final User2 value2 = mapper.readValue(jsonBytes, User2.class);
        System.out.println(value2);
        // 从字符串中读取
        final User2 value3 = mapper.readValue(jsonStr, User2.class);
        System.out.println(value3);
    }

}
