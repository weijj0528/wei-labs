package com.weiun.labs.jackson.pojo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 * The type User 1.
 * 测试类2:注解的使用
 * JsonIgnoreProperties注解忽略指定属性
 *
 * @author William.Wei
 */
@JsonIgnoreProperties({"young"})
public class User2 {

    /**
     * JsonProperty注解指定JSON属性名，index可以指定属性排序，值越小排序越靠前
     */
    @JsonProperty(value = "AGE", index = 1)
    private int age;

    /**
     * JsonIgnore注解排序某些属性
     */
    @JsonIgnore
    private BigDecimal balance;

    private boolean young;

    /**
     * JacksonXmlText注解将属性直接作为未被标签包裹的普通文本表现
     */
    @JacksonXmlText
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday1;

    /**
     * JacksonXmlProperty注解为序列化为XML格式时指定是为属性还是标签
     */
    @JacksonXmlProperty(isAttribute = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday2;

    public User2() {
    }

    public static User2 createInstance() {
        final User2 user = new User2();
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
