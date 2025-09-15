package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //应用程序在运行时，会自动为该接口创建一个实现类对象（代理对象），并且会自动将该实现类对象存入IOC容器中 --- bean
public interface UserMapper {

    /**
     * 查询所有用户
     */
    public List<User> findAll();

    /**
     * 根据id删除用户
     * #{id}占位符,等待传进来的参数
     */
    public Integer deleteById(Integer id);

    /**
     * 插入用户信息
     */
    public Integer insert(User user);
    /**
     * 根据id修改用户信息
     */
    public Integer update(User user);
    /**
     * 根据id查询用户信息
     */
    public User findByUsernameAndPassword(String username,String password);

}
