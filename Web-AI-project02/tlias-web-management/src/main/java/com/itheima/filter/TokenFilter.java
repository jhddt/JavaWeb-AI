package com.itheima.filter;

import com.itheima.pojo.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {
    /**
     *
     * @param servletRequest 请求对象
     * @param servletResponse 响应对象
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 将ServletRequest和ServletResponse强转为HttpServletRequest和HttpServletResponse
        // 原因：我们需要使用HTTP特有的方法，如获取请求URI、请求头等信息
        // 而这些方法在父接口ServletRequest中并不存在，只有在HttpServletRequest中才有
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String getRequestURI = request.getRequestURI(); //获取URI路径（/emp/login）

        //2.判断请求是否是登录请求（看请求路径中是否有/login），是登录操作就放行
        if(getRequestURI.contains("/login")){
            log.info("登录请求，放行...");
            filterChain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，返回错误信息（响应401状态码）
        if(token == null || token.equals("")){
            log.info("令牌为空，响应401状态码...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //5.如果token存在，校验令牌，如果校验失败，也是返回错误信息（响应401状态码）
        try {
            Claims claims = JwtUtils.parseJWT(token); //解析令牌
            Integer empId = Integer.valueOf(claims.get("id").toString()); //获取员工ID
            CurrentHolder.setCurrentId(empId);// 将获取员工ID放入ThreadLocal
            log.info("当前登录的员工ID：{}将其存入ThreadLocal", empId);
        } catch (Exception e) {
            log.info("令牌非法，响应401状态码...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.校验通过，放行
        log.info("令牌验证通过，放行");
        filterChain.doFilter(request,response);

        //7.删除ThreadLocal中的数据(放行之后再删除)
        log.info("删除ThreadLocal中的数据......");
        CurrentHolder.remove(); // 删除ThreadLocal中的数据, 因为当前线程已经处理完毕
    }
}
