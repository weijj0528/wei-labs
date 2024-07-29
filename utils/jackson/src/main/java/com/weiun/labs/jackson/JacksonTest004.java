package com.weiun.labs.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weiun.labs.jackson.pojo.User1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Jackson test 001.
 * 对集合类型的支持
 *
 * @author William.Wei
 */
public class JacksonTest004 {

    public static void main(String[] args) throws Exception {
        // ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // 注册类型模块以支持Java8时间日期类型，需要引入com.fasterxml.jackson.datatype:jackson-datatype-jsr310包
        // 还有其他类型模块，可按需引入：https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype
        mapper.registerModule(new JavaTimeModule());
        // 也可以自动搜索
        mapper.findAndRegisterModules();
        //
        List<User1> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(User1.createInstance());
        }
        // 序列化为字符串
        String jsonStr = mapper.writeValueAsString(userList);
        System.out.println(jsonStr);
        // 反序列化
        final List list = mapper.readValue(jsonStr, List.class);
        System.out.println(list);
        System.out.println(list.get(0).getClass());
        // 集合类型丢失需要手动指定
        final List<User1> listUser = mapper.readValue(jsonStr, new TypeReference<List<User1>>() {
        });
        System.out.println(listUser);
        System.out.println(listUser.get(0).getClass());
        // Map 类型
        Map<String, User1> userMap = new HashMap<>(8);
        userMap.put("user1", User1.createInstance());
        jsonStr = mapper.writeValueAsString(userMap);
        System.out.println(jsonStr);
        final JsonNode jsonNode = mapper.readTree(jsonStr);
        final JsonNode node = jsonNode.get("user1");
        System.out.println(node);
    }

}
