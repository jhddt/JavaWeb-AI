package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    /**
     * 分页查询班级信息
     * @param clazzQueryParam 班级查询参数对象，包含分页信息、班级名称、时间范围等查询条件
     * @return PageResult<Clazz> 分页结果对象，包含总记录数和当前页的班级列表
     */
    PageResult<Clazz> getClazzDatas(ClazzQueryParam clazzQueryParam);

    /**
     * 根据ID查询班级信息
     * @param id 班级ID
     * @return Clazz 班级对象
     */
    Clazz getId(Integer id);

    /**
     * 查询所有班级信息
     * @return List<Clazz> 班级列表
     */
    List<Clazz> selectAll();

    /**
     * 新增班级数据
     * @param clazz 班级对象，包含班级的基本信息
     */
    void addClazzData(Clazz clazz);

    /**
     * 更新班级信息
     * @param clazz 班级对象，包含需要更新的班级信息
     */
    void updateClazzData(Clazz clazz);

    /**
     * 根据ID删除班级信息
     * @param id 班级ID
     */
    void deleteClazzData(Integer id);

}
