package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门信息
     * @return 部门列表
     */
    List<Dept> findAll();

    /**
     * 根据ID删除部门
     * @param id 部门ID
     */
    void deleteByID(Integer id);

    /**
     * 新增部门
     * @param dept 部门对象，包含部门名称等信息
     */
    void insertByName(Dept dept);

    /**
     * 根据ID查询部门信息
     * @param id 部门ID
     * @return 部门对象
     */
    Dept findById(Integer id);

    /**
     * 更新部门信息
     * @param dept 部门对象，包含需要更新的信息
     */
    void update(Dept dept);

}
