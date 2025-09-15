package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/**
 * @Controller+@ResponseBody
 * @ResponseBondy -> 作用：将controller返回值直接作为响应体的数据直接响应；
 * 如果返回值是对象/集合，注解会先将数据转为json再响应回去。
 */

public class UserController {

    //方式一：属性注入
    @Autowired
    @Qualifier("userServiceImpl2")
    private UserSrevice userSrevice;

    //方式二：构造器注入
    //final代表变量初始化完毕就无法改变了
//    private final UserSrevice userSrevice;
//    @Autowired //--->如果当前类中只存在一个构造函数，@Autowired可以省略
//    public UserController(UserSrevice userSrevice) {
//        this.userSrevice = userSrevice;
//    }


//    //方法三：setter注入
//    @Autowired
//    private UserSrevice userSrevice;
//    public void setUserSrevice(UserSrevice userSrevice) {
//        this.userSrevice = userSrevice;
//    }

    /*    @RequestMapping("/list")
    public List<User> list() throws Exception{
        //1.加载并读取文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());

        //2.解析数据，封装成对象 --> 集合
        List<User> userList = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            return new User(id, username, password, name, age, updateTime);
        }).toList();//(16版本以后才可以使用)
        .collect(Collectors.toList());

        //3.响应数据
        //return JSONUtil.toJsonStr(userList, JSONConfig.create().setDateFormat("yyyy-MM-dd HH:mm:ss"));
        return userList;
    }*/


    @RequestMapping("/list")
    public List<User> list() throws Exception {
        //1.调用Service层
        List<User> userList = userSrevice.findAll();
        //2.响应数据
        return userList;
    }
}
