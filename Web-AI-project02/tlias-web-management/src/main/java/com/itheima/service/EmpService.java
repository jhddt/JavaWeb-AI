package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
    PageResult<Emp> pageSelect(EmpQueryParam empQueryParam);

    /**
     * 添加员工信息
     */
    void addEmp(Emp emp);

    /**
     * 根据ID批量删除员工信息
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询员工信息以及员工工作经历信息
     */
    Emp getInfo(Integer id);

    /**
     * 修改员工数据
     */
    void update(Emp emp);

    /**
     * 查询所有员工信息
     */
    List<Emp> selectAll();
}
