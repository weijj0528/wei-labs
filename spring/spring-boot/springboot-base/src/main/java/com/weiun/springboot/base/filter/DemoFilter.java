package com.weiun.springboot.base.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author William
 * @Date 2019/2/27
 * @Description
 */

@WebFilter(urlPatterns = "/*", filterName = "testFilter")
public class DemoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Object name = filterConfig.getServletContext().getInitParameter("name");
        System.out.println(name);
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

    }
}
