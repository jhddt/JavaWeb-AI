package com.itheima.service.impl;

import com.itheima.mapper.UserDao;
import com.itheima.pojo.User;
import com.itheima.service.UserSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//@Component
@Service
public class UserServiceImpl implements UserSrevice {
    @Autowired//应用程序运行时，会自动查询该类型的bean对象，并赋值给该成员变量
    private UserDao userDao;
    @Override
    public List<User> findAll() {
        //1.调用dao，获取原始数据
        List<String> lines = userDao.findall();

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
        /*.collect(Collectors.toList());*/
        return userList;
    }
}
