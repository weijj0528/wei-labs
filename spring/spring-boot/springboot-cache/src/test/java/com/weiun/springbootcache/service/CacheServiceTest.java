package com.weiun.springbootcache.service;

import com.weiun.springbootcache.SpringbootCacheApplicationTests;
import com.weiun.springbootcache.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CacheServiceTest extends SpringbootCacheApplicationTests {


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisTemplate<String, User> userRedisTemplate;

    @Test
    public void stringCacheTest() {
        String key = "test";
        String value = "Hello Cache!";
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
        String cacheValue = (String) redisTemplate.opsForValue().get(key);
        Assert.assertTrue(value.equals(cacheValue));
    }

    @Test
    public void objCacheTest() {
        User user = new User();
        user.setName("William");
        user.setAge(18);
        user.setBirthday(new Date());
        String key = "test:obj";
        userRedisTemplate.opsForValue().set(key, user, 60, TimeUnit.SECONDS);
        User u = userRedisTemplate.opsForValue().get(key);
        Assert.assertTrue(user.getName().equals(u.getName()) && user.getAge() == u.getAge());
    }


}