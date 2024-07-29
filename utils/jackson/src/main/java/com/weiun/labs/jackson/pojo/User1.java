package com.weiun.labs.jackson.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * 测试类1
 * The type User 1.
 *
 * @author William.Wei
 */
public class User1 {

    private int age;

    private BigDecimal balance;

    private boolean young;

    private String name;

    private Date birthday1;

    private LocalDateTime birthday2;

    public User1() {
    }

    public static User1 createInstance() {
        final User1 user = new User1();
        final Random random = new Random();
        user.age = random.nextInt(80);
        user.balance = BigDecimal.valueOf(random.nextDouble());
        user.young = random.nextBoolean();
        user.name = "user:" + random.nextInt();
        user.birthday1 = new Date();
        user.birthday2 = LocalDateTime.now();
        return user;
    }

    @Override
    public String toString() {
        return "User1{" +
                "age=" + age +
                ", balance=" + balance +
                ", young=" + young +
                ", name='" + name + '\'' +
                ", birthday1=" + birthday1 +
                ", birthday2=" + birthday2 +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isYoung() {
        return young;
    }

    public void setYoung(boolean young) {
        this.young = young;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(Date birthday1) {
        this.birthday1 = birthday1;
    }

    public LocalDateTime getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(LocalDateTime birthday2) {
        this.birthday2 = birthday2;
    }
}
