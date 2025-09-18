package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzSevicImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 分页查询全部班级信息
     * PageHelper是分页查询的插件，可以自动算出页码
     */
    @Override
    public PageResult<Clazz> getClazzDatas(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam.getName(),clazzQueryParam.getBegin(),clazzQueryParam.getEnd());

        // 设置班级状态
        LocalDate now = LocalDate.now();
        for (Clazz clazz : clazzList) {
            if (clazz.getEndDate() != null && now.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate() != null && now.isBefore(clazz.getBeginDate())) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }

        //3.解析查询参数并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    /**
     * 根据主键ID查询班级的信息
     */
    @Override
    public Clazz getId(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 查询所有班级信息
     */
    @Override
    public List<Clazz> selectAll() {
        return clazzMapper.selectAll();
    }

    /**
     * 新增班级数据
     */
    @Override
    public void addClazzData(Clazz clazz) {
        //1.补全基本数据
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.调用mapper添加班级信息
        clazzMapper.addClazzData(clazz);
    }

    /**
     * 修改班级信息
     */
    @Override
    public void updateClazzData(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazzData(clazz);
    }

    /**
     *根据id删除班级信息
     * @param id
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteClazzData(Integer id) {
        clazzMapper.deleteClazzData(id);
    }
}
