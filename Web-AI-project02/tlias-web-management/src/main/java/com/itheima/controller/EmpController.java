package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理的Controller
 */

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping
    public Result pageSelect(EmpQueryParam empQueryParam){
        log.info("分页查询的页码和每一页的大小：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.pageSelect(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("前端传入的参数：{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }
}
