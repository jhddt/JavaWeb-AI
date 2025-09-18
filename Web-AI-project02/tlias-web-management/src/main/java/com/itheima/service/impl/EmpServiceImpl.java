package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     * 原始分页查询
     * @param page 页码
     * @param pageSize 每页展示的记录数
     * @return
     */
/*    @Override
    public PageResult<Emp> pageSelect(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {
        //1.调用mapper接口查询总记录数
        Long total = empMapper.count();
        //2.调用mapper接口查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        //3.封装结果
        return new PageResult<Emp>(total,rows);
    }*/

    /**
     * PageHelper进行分页查询
     * 1.mapper的SQL语句结尾不能加分号（;）
     * 2.PageHelper仅能对跟在其后面的第一个mapper起作用
     */
    public PageResult<Emp> pageSelect(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam.getName(),empQueryParam.getGender(),empQueryParam.getBegin(),empQueryParam.getEnd());
        //3.解析查询参数并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    /**
     * 添加员工信息
     */
    //@Transactional默认出现运行时异常才会回滚
    @Transactional(rollbackFor = {Exception.class}) //加上rollbackFor = {Exception.class}遇到所有异常都会回滚
    @Override
    public void addEmp(Emp emp) {
        try {
            //1.补全基本数据
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //2.添加员工的基本信息
            empMapper.addEmp(emp);
            //3.添加员工工作经历---批量
            Integer empId = emp.getId();//获取新添加的员工id
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){//如果有工作经历就执行
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));//为工作经历设置员工id
                empExprMapper.addEmpExpr(exprList);
            }
        } finally {
            //记录日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

    /**
     * 批量删除员工信息
     */
    @Transactional(rollbackFor = {Exception.class})//出现任何异常都需要回滚
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工的信息
        empMapper.deleteByIds(ids);
        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 根据id查询员工信息以及员工工作经历信息
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工数据
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.根据员工ID修改员工的工作经历信息
        //2.1先根据员工ID删除原有的工作经历信息
        empMapper.deleteByIds(Arrays.asList(emp.getId()));
        //2.2再根据员工ID添加新的员工经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.addEmpExpr(exprList);
        }
    }

    /**
     * 查询所有员工信息
     */
    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }
}
