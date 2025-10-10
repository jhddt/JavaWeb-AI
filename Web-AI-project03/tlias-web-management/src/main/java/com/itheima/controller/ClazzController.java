package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")

public class ClazzController {

    @Autowired
    private ClazzService clazzService;


    /**
     * 分页查询所有班级信息
     */
    @GetMapping("/list")
    public Result selectAll(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList = clazzService.selectAll();
        return Result.success(clazzList);
    }


    /**
     * 条件分页查询接口
     */
    @GetMapping
    public Result getClazzDatas(ClazzQueryParam clazzQueryParam){
        log.info("条件分页查询接口：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.getClazzDatas(clazzQueryParam);
        return Result.success(pageResult);
    }


    /**
     * 根据主键ID查询班级的信息
     */
    @GetMapping("/{id}")
    public Result getByID(@PathVariable("id") Integer id){
        log.info("查询班主任姓名：{}",id);
        Clazz clazz = clazzService.getId(id);
        return Result.success(clazz);
    }


    /**
     * 新增班级接口
     */
    @Log
    @PostMapping
    public Result addClazzData(@RequestBody Clazz clazz){
        log.info("添加班级信息：{}",clazz);
        clazzService.addClazzData(clazz);
        return Result.success();
    }

    /**
     * 修改班级信息
     */
    @Log
    @PutMapping
    public Result updateClazzData(@RequestBody Clazz clazz){
        log.info("修改班级数据：{}",clazz);
        clazzService.updateClazzData(clazz);
        return Result.success();
    }


    /**
     * 根据id删除班级信息
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteClazzData(@PathVariable("id") Integer id){
        log.info("根据id删除班级信息");
        clazzService.deleteClazzData(id);
        return Result.success();
    }

}
