package com.weiun.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenFilter extends OncePerRequestFilter {


    @Override
    @SuppressWarnings("unchecked")
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        log.info("TokenFilter:{}", request.getRequestURI());
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            unAuthorization(response);
        }
    }

    private void unAuthorization(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write("{\"code\": 401, \"msg\": \"认证失败, 请重新登录!\"}");
        response.getWriter().flush();
    }
}
