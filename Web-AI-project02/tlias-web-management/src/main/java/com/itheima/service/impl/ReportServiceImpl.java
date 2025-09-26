package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 统计职位人数信息
     */
    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口获取统计数据
        List<Map<String, Object>> list = empMapper.countJobEmpData();
        //2.组装结果并返回
        //解析List集合获取集合中职位和职位人数的集合
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    /**
     * 统计员工性别信息
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /**
     * 班级人数统计
     */
    @Override
    public ClazzOption studentCountData() {
        //1.调用mapper接口获取统计数据
        List<Map<String, Object>> list = clazzMapper.studentCountData();
        //2.组装结果并返回
        //解析List集合获取集合中职位和职位人数的集合
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzOption(clazzList,dataList);
    }

    /**
     * 统计学员学历信息
     */
    @Override
    public List<Map<String, Object>> studentDegreeData() {
        return studentMapper.studentDegreeData();
    }

    /**
     * 统计员工操作的日志信息
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<OperateLog> getlogPage(Integer page, Integer pageSize) {
        //设置分页操作
        PageHelper.startPage(page,pageSize);
        List<OperateLog> operateLogList = operateLogMapper.selectAllOperateLog();
        //解析分页参数并封装
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
