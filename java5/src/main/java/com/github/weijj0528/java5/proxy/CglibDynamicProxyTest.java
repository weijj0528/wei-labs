package com.github.weijj0528.java5.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * The type Java dynamic proxy test.
 * Java动态代理
 *
 * @author William.Wei
 */
public class CglibDynamicProxyTest {

    /**
     * The entry point of application.
     * 代理的类不能为final,否则报错
     * 目标对象的方法如果为final/static,那么就不会被拦截,即不会执行目标对象额外的业务方法.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        final IUserDao userDao = new UserDao();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);
        enhancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                // 可以对方法进行判断
                System.out.println("Before Method Invoke");
                Object invoke = method.invoke(userDao, objects);
                System.out.println("After Method Invoke");
                return invoke;
            }
        });
        IUserDao userDaoProxy = (IUserDao) enhancer.create();
        userDaoProxy.save();
    }

}


