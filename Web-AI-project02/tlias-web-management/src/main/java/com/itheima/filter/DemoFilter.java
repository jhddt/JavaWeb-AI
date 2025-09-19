package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*") //拦截所有请求
@Slf4j
public class DemoFilter implements Filter {

    //过滤器初始化方法，在web服务器启动的时候执行，执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init初始化的方法执行了......");
    }

    //拦截到请求后执行，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到请求......");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    //销毁方法，在web服务器关闭的时候执行，执行一次
    @Override
    public void destroy() {
        log.info("destroy销毁的方法执行了......");
    }
}
