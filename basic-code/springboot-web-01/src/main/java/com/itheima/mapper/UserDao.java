package com.itheima.mapper;

import java.util.List;

public interface UserDao {
    /**
     *
     * 从文件中加载用户数据
     * @return
     */
    public List<String> findall();
}
