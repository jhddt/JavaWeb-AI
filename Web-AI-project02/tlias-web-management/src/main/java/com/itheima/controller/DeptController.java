package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)

    /**
     * 查询部门信息
     * @return
     */
    @GetMapping
    public Result list(){
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 根据id删除部门信息   方法一：根据HttpServletRequest获取请求参数
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.getInteger(idStr);
//        log.info("根据id删除部门：{}",id);
//        return Result.success();
//    }

    /**
     * 根据id删除部门信息   方法二：根据@RequestParam获取请求参数
     * 一旦声明@RequestParam，该参数在请求时必须传递，如果不传递将会报错。（默认 required 为 true）
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer deptId){
//        log.info("根据id删除部门：{}",id);
//        return Result.success();
//    }

    /**
     * 根据id删除部门信息   方法三：根据@RequestParam获取请求参数
     * 如果请求参数的名称和形参变量名相同，直接定义方法形参即可接收。
     */
    @Log
    @DeleteMapping
    public Result deleteById(Integer id){
        log.info("根据id删除部门：{}",id);
        deptService.deleteByID(id);
        return Result.success();
    }

    /**
     * 添加部门信息
     * @RequestBody可以将json数据封装成对象参数传递
     */
    @Log
    @PostMapping
    public Result insertByName(@RequestBody Dept dept){
        log.info("添加部门信息：{}",dept);
        deptService.insertByName(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门信息
     * @PathVariable用来获取前端传回来的路径参数
     */
    @GetMapping("/{id}")
    public Result findByID(@PathVariable("id") Integer id){
        Dept dept = deptService.findById(id);
        log.info("查询成功！");
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门数据：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
