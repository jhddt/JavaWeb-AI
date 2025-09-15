package com.itheima.springbootwebreqresp.controller;

import com.itheima.springbootwebreqresp.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
//@RestController=@Controller+@ResponseBody

public class RequestController {
/*
    //简单参数
    //原始方法
    @RequestMapping("/simpleParam")
    public String simpleParam(HttpServletRequest request){
        //获取请求参数
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        //类型转换
        int age = Integer.parseInt(ageStr);
        System.out.println(name + ":" + age);
        return "ok";
    }*/


/*    //基于springboot方式修改代码
    @RequestMapping("/simpleParam")
    //springboot会自动进行类型转换
    public String simpleParam(String name, Integer age){
        System.out.println(name + ":" + age);
        return "ok";
    }*/


    @RequestMapping("/simpleParam")
//    @RequestParam(name="name")String username参数名称映射
//    required = false代表这个参数可以不传递
    public String simpleParam(@RequestParam(name="name",required = false)String username, Integer age){
        System.out.println(username + ":" + age);
        return "ok";
    }


    //实体参数
    @RequestMapping("/entityParam")
    public String entityParam(User user){
        System.out.println(user);
        return "OK";
    }


    //复杂参数
    @RequestMapping("/complexParam")
    public String complexParam(User user){
        System.out.println(user);
        return "OK";
    }


    //数组参数
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }


    //集合参数
    @RequestMapping("/listParam")
//    @RequestParam绑定参数关系
    public String listParam(@RequestParam ArrayList<String> hobby){
        System.out.println(hobby);
        return "OK";
    }


    //日期时间参数
    @RequestMapping("/dateParam")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")指定日期格式为：2025-08-18 16:00:59
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        System.out.println(updateTime);
        return "OK";
    }


    //json参数
    @RequestMapping("/bodyParam")
    //@RequestBody将json的数据封装到实体对象中
    public String bodyParam(@RequestBody User user){
        System.out.println(user);
        return "OK";
    }


    //路径参数
    @RequestMapping("/pathParam/{id}/{name}")
    //@PathVariable获取路径参数
    public String pathParam(@PathVariable Integer id,@PathVariable String name){
        System.out.println(id + ":" + name);
        return "OK";
    }
}

