package com.weiun.java.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author William
 * @Date 2019/2/25
 * @Description 监听器
 */
public class DemoListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        String requestURI = servletRequest.getRequestURI();
        System.out.println("requestInitialized:" + requestURI);
    }
}
