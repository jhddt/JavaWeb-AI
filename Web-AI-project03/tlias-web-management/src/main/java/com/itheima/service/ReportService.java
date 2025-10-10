package com.itheima.service;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计职位人数信息
     * @return JobOption 职位统计信息对象，包含各职位的名称和对应人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     * @return List<Map<String, Object>> 性别统计数据列表，每个Map包含性别和对应人数
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 统计班级学生人数信息
     * @return ClazzOption 班级学生人数统计信息对象
     */
    ClazzOption studentCountData();

    /**
     * 统计学生学历分布信息
     * @return List<Map<String, Object>> 学历统计数据列表，每个Map包含学历和对应人数
     */
    List<Map<String, Object>> studentDegreeData();

    /**
     * 统计员工操作的日志信息
     * @param page
     * @param pageSize
     * @return
     */
    PageResult<OperateLog> getlogPage(Integer page, Integer pageSize);
}
