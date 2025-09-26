package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController

public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 统计职位人数信息
     */
    @GetMapping("/report/empJobData")
    public Result countJob(){
        log.info("统计职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     */
    @GetMapping("/report/empGenderData")
    public Result countgender(){
        log.info("统计员工性别信息");
        List<Map<String, Object>> genderDate = reportService.getEmpGenderData();
        return Result.success(genderDate);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/report/studentCountData")
    public Result studentCountData(){
        log.info("统计班级人数");
        ClazzOption clazzOption = reportService.studentCountData();
        return Result.success(clazzOption);
    }

    /**
     * 统计学员学历信息
     */
    @GetMapping("/report/studentDegreeData")
    public Result studentDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String, Object>> degreeDate = reportService.studentDegreeData();
        return Result.success(degreeDate);
    }

    /**
     * 日志列表查询
     */
    @GetMapping("/log/page")
    public Result getlogPage(Integer page,Integer pageSize){
        log.info("查询日志列表信息");
        PageResult<OperateLog> operateLogList = reportService.getlogPage(page,pageSize);
        return Result.success(operateLogList);
    }
}
