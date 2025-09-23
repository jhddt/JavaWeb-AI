package com.itheima.config;

import com.itheima.filter.DemoInterceptor;
import com.itheima.filter.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 配置类（内含@Component注解）
public class WebConfig implements WebMvcConfigurer {
    // 添加拦截器
//    @Autowired
//    private DemoInterceptor demoInterceptor;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器对象
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")// 拦截所有请求
                .excludePathPatterns("/login"); // 排除登录接口
    }
}
