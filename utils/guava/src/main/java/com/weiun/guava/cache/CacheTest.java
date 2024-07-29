package com.weiun.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author William
 * @Date 2019/3/11
 * @Description 缓存
 */
public class CacheTest {

    public static void main(String[] args) throws ExecutionException {
        Cache<Integer, User> build = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build(new CacheLoader<Integer, User>() {
                    @Override
                    public User load(Integer key) throws Exception {
                        System.out.println("Create:" + key);
                        User user = new User();
                        user.setName("Test" + key);
                        user.setId(key);
                        user.setAge(key);
                        return user;
                    }
                });

        User user = ((LoadingCache<Integer, User>) build).get(1);
        System.out.println(user.getName());
        System.out.println(user.getAge());
        user = ((LoadingCache<Integer, User>) build).get(1);
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

}
