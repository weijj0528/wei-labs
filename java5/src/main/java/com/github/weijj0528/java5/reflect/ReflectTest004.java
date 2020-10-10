package com.github.weijj0528.java5.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author William
 * @Date 2019/3/19
 * @Description 反射其他
 */
public class ReflectTest004 {


    public static void main(String[] args) throws Exception {
        // 获取类
        Class<?> clazz = Class.forName("com.weiun.reflect.bean.User");
        // 获取父类型(同接口)
        Class<?> superclass = clazz.getSuperclass();
        // 获取接口类型
        Class<?>[] interfaces = clazz.getInterfaces();
        // 获取带参数的父类型 获取泛型类型
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(actualTypeArguments[0]);
        System.out.println(genericSuperclass);
        // 获取带参数的接口 获取泛型类型
        Type[] genericInterfaces = clazz.getGenericInterfaces();
    }


}
