package com.weiun.springbootcache.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @createTime 2019/3/3 11:43
 * @description
 */
@Data
public class User implements Serializable {

    private String name;

    private int age;

    private Date birthday;

}
