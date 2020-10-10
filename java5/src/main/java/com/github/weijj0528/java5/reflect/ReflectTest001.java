package com.github.weijj0528.java5.reflect;


import com.github.weijj0528.java5.reflect.bean.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Field
 *
 * @author William
 * @Date 2019/3/19
 * @Description 反射属性操作
 */
public class ReflectTest001 {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // 获取Public属性,能获取到父类的属性
        Field[] fields = User.class.getFields();
        printField(fields);
        System.out.println("-----------------------------");
        // 获取所有属性，不能获取父类的属性
        Field[] declaredFields = User.class.getDeclaredFields();
        printField(declaredFields);
        // 值设置
        System.out.println("-----------------------------");
        User user = new User();
        Field a = User.class.getField("a");
        a.set(user, "你好");
        System.out.println(user.getAge());

        // 静态属性判断
        boolean aStatic = Modifier.isStatic(a.getModifiers());
        System.out.println(aStatic);
    }

    private static void printField(Field[] declaredFields) {
        for (Field field : declaredFields) {
            Class<?> type = field.getType();
            System.out.println(field.getName() + " type " + type.getName());
        }
    }
}
