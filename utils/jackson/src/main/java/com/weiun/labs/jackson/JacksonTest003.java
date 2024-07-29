package com.weiun.labs.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weiun.labs.jackson.pojo.User1;

/**
 * The type Jackson test 001.
 * 特性配置，可以从以下特性枚举类中查看支持的特性
 * {@link MapperFeature}
 * {@link SerializationFeature}
 * {@link DeserializationFeature}
 * {@link JsonParser.Feature}
 * {@link JsonGenerator.Feature}
 *
 * @author William.Wei
 */
public class JacksonTest003 {

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
        // 美化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 允许序列化空的POJO类
        // （否则会抛出异常）
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 把java.util.Date, Calendar输出为数字（时间戳）
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 在遇到未知属性的时候不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 强制JSON 空字符串("")转换为null对象值:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许没有引号的字段名（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 强制转义非ASCII字符
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        // 将内容包裹为一个JSON属性，属性名默认为类名或由@JsonRootName注解指定
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        // 序列化为字符串
        final String jsonStr = mapper.writeValueAsString(user);
        System.out.println(jsonStr);
    }

}
