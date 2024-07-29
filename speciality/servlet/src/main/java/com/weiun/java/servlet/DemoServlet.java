package com.weiun.java.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author William
 * @Date 2019/2/21
 * @Description Java Servlet
 */
public class DemoServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("DemoServlet init!");
        Enumeration<String> initParameterNames = config.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String s = initParameterNames.nextElement();
            String initParameter = config.getInitParameter(s);
            System.out.println(String.format("InitParam:(%s:%s)", s, initParameter));
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.println("Hello World!");
        out.flush();
        out.close();
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("DemoServlet destroy!");
    }
}
