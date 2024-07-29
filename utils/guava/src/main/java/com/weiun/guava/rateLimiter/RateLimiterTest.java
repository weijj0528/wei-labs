package com.weiun.guava.rateLimiter;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author William
 * @Date 2019/3/20
 * @Description
 */
public class RateLimiterTest {


    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(0.002);
        System.out.println(rateLimiter.tryAcquire());
        System.out.println(rateLimiter.tryAcquire());
    }
}
