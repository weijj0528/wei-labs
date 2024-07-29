package com.weiun.springbootcache.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.weiun.springbootcache.pojo.User;
import org.junit.Test;

/**
 * @author Administrator
 * @createTime 2019/3/3 14:02
 * @description
 */
public class FastJsonTest {

    @Test
    public void test() {
        String value = "{\"@type\":\"com.weiun.springbootcache.pojo.User\",\"age\":18,\"name\":\"William\"}";
        // 自动类型转换
        // 1、指定包
        //ParserConfig.getGlobalInstance().addAccept("com.weiun.springbootcache.pojo");
        // 2、所有（推荐）
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Object o = JSON.parseObject(value, Object.class);
        System.out.println(o instanceof User);
        System.out.println(o);
    }

}
