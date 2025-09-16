package com.itheima.controller;

import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
