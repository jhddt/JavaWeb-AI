package com.itheima.controller;

import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询全部员工信息
     */
    @GetMapping("/list")
    public Result selectAll(){
        log.info("查询所有员工信息");
        List<Emp> empList = empService.selectAll();
        return Result.success(empList);
    }


    /**
     * 分页查询所有员工信息
     */
    @GetMapping
    public Result pageSelect(EmpQueryParam empQueryParam){
        log.info("分页查询的页码和每一页的大小：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.pageSelect(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加员工信息
     */
    @PostMapping
    public Result addEmp(@RequestBody Emp emp){
        log.info("前端传入的参数：{}",emp);
        empService.addEmp(emp);
        return Result.success();
    }

    /**
     * 删除员工信息
     */
    //用数组的方式接收数据
/*    @DeleteMapping("/{id}")
    public Result delete(Integer[] ids){
        log.info("需要删除的员工ID：{}", Arrays.toString(ids));
        return Result.success();
    }*/
    //用List集合的方式接受数据
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("需要删除的员工ID：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工信息以及员工工作经历信息（为修改员工信息查询回显）
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        log.info("查询的ID为：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 修改员工信息
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改的数据：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
