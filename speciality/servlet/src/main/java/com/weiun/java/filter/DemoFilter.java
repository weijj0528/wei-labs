package com.weiun.java.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author William
 * @Date 2019/2/25
 * @Description 过滤器
 */
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig config) {
        // 初始化参数
        System.out.println("DemoFilter init!");
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            String initParameter = config.getInitParameter(s);
            System.out.println(String.format("InitParam:(%s:%s)", s, initParameter));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // servletRequest 处理
        System.out.println("doFilter before do something");
        // 执行Servlet
        filterChain.doFilter(servletRequest, servletResponse);
        // servletResponse 处理
        System.out.println("doFilter after do something");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
