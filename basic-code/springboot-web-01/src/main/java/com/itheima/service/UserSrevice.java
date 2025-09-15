package com.itheima.service;

import com.itheima.pojo.User;

import java.util.List;

public interface UserSrevice {
    /**
     * 查询所有用户信息
     *
     * @return
     */
    public List<User> findAll();
}
