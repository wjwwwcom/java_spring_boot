package com.hqyj.JavaSpringBoot.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class RequestViewInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==== Pre interceptor ====");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("==== Post interceptor ====");

        if (modelAndView == null || modelAndView.getViewName().startsWith("redirect")) {
            return;
        }

        String path = request.getServletPath();
        String template = (String) modelAndView.getModelMap().get("template");
        if (StringUtils.isBlank(template)) {
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            modelAndView.getModelMap().addAttribute(
                    "template", path.toLowerCase());
        }

        HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("==== After interceptor ====");
        HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
