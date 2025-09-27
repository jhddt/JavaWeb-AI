package com.aliyun.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AliyunOSS的自动配置类
 */
@EnableConfigurationProperties(AliyunOSSProperties.class) //它的底层封装了@Import注解
@Configuration
public class AliyunOSSAutoConfigration {

    @Bean
    @ConditionalOnMissingBean //当容器中没有这个Bean的时候，才会创建这个Bean
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties){
        return new AliyunOSSOperator(aliyunOSSProperties);
    }
}
