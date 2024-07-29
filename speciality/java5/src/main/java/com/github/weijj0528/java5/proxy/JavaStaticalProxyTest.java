package com.github.weijj0528.java5.proxy;

/**
 * Java静态代理
 *
 * @author William.Wei
 */
public class JavaStaticalProxyTest {

    /**
     * The entry point of application.
     * 1.1 静态代理实现
     * 1.2 实现相同的接口
     * 1.3 代理对象依赖目标对象
     * 1.4 代理对象调用目标对象实现实现接口方法，可额外新增逻辑
     * 1.5 优点：可以做到在不修改目标对象的功能前提下,对目标功能扩展
     * 1.6 缺点：因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        IUserDao userDao = new UserDaoProxy(new UserDao());
        userDao.save();
    }

}

/**
 * The type User dao proxy.
 */
class UserDaoProxy implements IUserDao {

    private UserDao userDao;

    /**
     * Instantiates a new User dao proxy.
     *
     * @param userDao the user dao
     */
    public UserDaoProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        System.out.println("UserDaoProxy:开始保存！");
        userDao.save();
        System.out.println("UserDaoProxy:保存结束！");
    }
}

