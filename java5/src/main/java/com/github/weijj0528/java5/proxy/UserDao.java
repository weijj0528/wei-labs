package com.github.weijj0528.java5.proxy;

/**
 * The type User dao.
 */
class UserDao implements IUserDao {

    public void save() {
        System.out.println("UserDao：保存成功！");
    }
}
