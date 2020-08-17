package com.hqyj.JavaSpringBoot.filter;

import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
@WebFilter(filterName = "RequestParamaFilter", urlPatterns = "/**")
public class RequestParamaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Im init方法");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Im dofilter方法");
       HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpRequest){
            @Override
            public String getParameter(String name) {
                String value =httpRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values =httpRequest.getParameterValues(name);
                if (values !=null && values.length>0){
                    for (int i=0;i<values.length;i++){
                        values[i] =values[i].replaceAll("fuck","***");
                    }
                    return values;
                }
                return super.getParameterValues(name);
            }
        };
        filterChain.doFilter(wrapper,response);
    }

    @Override
    public void destroy() {
        System.out.println("Im destroy方法");

    }
}
