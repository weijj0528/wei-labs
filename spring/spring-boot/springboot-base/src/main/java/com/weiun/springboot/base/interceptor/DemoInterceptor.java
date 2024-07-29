package com.weiun.springboot.base.interceptor;

import com.weiun.springboot.base.config.WebMvcConfig;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * @author William
 * @Date 2019/2/27
 * @Description 拦截器示例
 */
@Component
public class DemoInterceptor extends HandlerInterceptorAdapter {

    private final Object lock = new Object();

    private Map<RequestMappingInfo, HandlerMethod> handlerMethods;

    private Set<RequestMappingInfo> requestMappingInfos;

    /**
     * 保证只有一个线程进行初始化
     *
     * @throws BeansException
     */
    private synchronized void init() throws BeansException {
        if (requestMappingInfos == null) {
            System.out.println("init");
            RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) WebMvcConfig.getBean("requestMappingHandlerMapping");
            handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
            requestMappingInfos = handlerMethods.keySet();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 返回false 将会拦截本次请求，不进行处理
        // 返回true 正常响应
        System.out.println("preHandle");
        if (requestMappingInfos == null) {
            init();
        }
        if (requestMappingInfos != null && !requestMappingInfos.isEmpty()) {
            RequestMappingInfo match = null;
            HandlerMethod handlerMethod = null;
            for (RequestMappingInfo requestMappingInfo : requestMappingInfos) {
                match = requestMappingInfo.getMatchingCondition(request);
                if (match != null) {
                    handlerMethod = handlerMethods.get(requestMappingInfo);
                    break;
                }
            }
            if (match != null) {
                // 找到匹配的接口签名与执行器，可进行鉴权操作
                Set<String> patterns = match.getPatternsCondition().getPatterns();
                System.out.println(patterns);
                System.out.println(handlerMethod);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        //System.out.println("ViewName:" + modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
