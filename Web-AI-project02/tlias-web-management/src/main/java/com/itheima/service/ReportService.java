package com.itheima.service;

import com.itheima.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计职位人数信息
     */
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();
}
