package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录验证拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求路径
        String getRequestURI = request.getRequestURI(); //获取URI路径（/emp/login）
        //2.判断请求是否是登录请求（看请求路径中是否有/login），是登录操作就放行
        if(getRequestURI.contains("/login")){
            log.info("登录请求，放行...");
            return true;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，如果不存在，返回错误信息（响应401状态码）
        if(token == null || token.equals("")){
            log.info("令牌为空，响应401状态码...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.如果token存在，校验令牌，如果校验失败，也是返回错误信息（响应401状态码）
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("令牌非法，响应401状态码...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.校验通过，放行
        log.info("令牌验证通过，放行");
        return true;
    }
}
