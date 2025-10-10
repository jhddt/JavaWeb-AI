package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 查询全部学员信息并分页展示
     * @param studentQueryParam
     * @return
     */
    @GetMapping
    public Result getStudentPageDatas(StudentQueryParam studentQueryParam){
        log.info("学员信息分页查询：{}",studentQueryParam);
        PageResult<Student> studentList = studentService.getStudentPageDatas(studentQueryParam);
        return Result.success(studentList);
    }

    /**
     * 删除学员信息
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result deleteStufents(@PathVariable("ids") Integer [] ids){
        log.info("要删除的学生学号：{}",ids);
        studentService.deleteStufents(ids);
        return Result.success();
    }

    /**
     * 添加学员
     */
    @Log
    @PostMapping
    public Result addStudentData(@RequestBody Student student){
        log.info("添加的学生的信息：{}",student);
        studentService.addStudentData(student);
        return Result.success();
    }

    /**
     * 根据id查询学员信息
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        log.info("要查询的学号为：{}",id);
        Student student= studentService.selectById(id);
        return Result.success(student);
    }

    /**
     * 修改学员信息
     */
    @Log
    @PutMapping
    public Result updateStudentData(@RequestBody Student student){
        log.info("要修改的学员的信息：{}",student);
        studentService.updateStudentData(student);
        return Result.success();
    }

    /**
     * 学员违纪处理
     */
    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandling(@PathVariable("id") String id, @PathVariable("score") String score){
        Integer stuID = Integer.parseInt(id);
        Integer stuScore = Integer.parseInt(score);
        log.info("学员id:{}违纪处理：{}",id,score);
        studentService.violationHandling(stuID,stuScore);
        return Result.success();
    }
}
