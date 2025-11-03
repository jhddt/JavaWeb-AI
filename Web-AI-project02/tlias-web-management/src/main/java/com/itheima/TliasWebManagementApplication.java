package com.itheima;

//import com.example.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


//自动配置实现方案二：
//@Import(TokenParser.class)//普通类
//@Import(HeaderConfig.class)//配置类，配置类中的所有Bean对象都会引入到IOC容器中
//@Import(MyImportSelector.class) //Import的实现类 - 实现批量导入
//@EnableHeaderConfig


////自动配置实现方案一：@Componet + @ComponetScan
//@ComponentScan(basePackages = {"com.example","com.itheima"})//指定组件扫描的范围


@ServletComponentScan//开启了SpringBoot对Servlet组件的支持
@SpringBootApplication
public class TliasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManagementApplication.class, args);
    }

}
