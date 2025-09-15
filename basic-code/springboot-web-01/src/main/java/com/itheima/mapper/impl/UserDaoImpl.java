package com.itheima.mapper.impl;

import cn.hutool.core.io.IoUtil;
import com.itheima.mapper.UserDao;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//@Component//将当前类交给IOC容器管理
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public List<String> findall() {
        //1.加载并读取文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
