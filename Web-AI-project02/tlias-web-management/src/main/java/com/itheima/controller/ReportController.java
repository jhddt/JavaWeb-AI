package com.itheima.controller;

import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RequestMapping("/report")
@RestController

public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 统计职位人数信息
     */
    @GetMapping("/empJobData")
    public Result countJob(){
        log.info("统计职位人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result countgender(){
        log.info("统计员工性别信息");
        List<Map<String, Object>> genderDate = reportService.getEmpGenderData();
        return Result.success(genderDate);
    }

    /**
     * 班级人数统计
     */
    @GetMapping("/studentCountData")
    public Result studentCountData(){
        log.info("统计班级人数");
        ClazzOption clazzOption = reportService.studentCountData();
        return Result.success(clazzOption);
    }

    /**
     * 统计学员学历信息
     */
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData(){
        log.info("统计学员学历信息");
        List<Map<String, Object>> degreeDate = reportService.studentDegreeData();
        return Result.success(degreeDate);
    }

    /**
     * 日志列表查询
     */
/*    @GetMapping("/page")
    public Result getlogPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询日志列表信息");
        reportService.getlogPage(page,pageSize);
    }*/
}
