package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询全部学员信息并分页展示
     */
    @Override
    public PageResult<Student> getStudentPageDatas(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2.执行查询
        List<Student> studentList = studentMapper.getStudentPageDatas(studentQueryParam.getName(),studentQueryParam.getDegree(),studentQueryParam.getClazzId());
        //3.解析查询参数并封装
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    /**
     *  删除学员信息
     */
    @Override
    public void deleteStufents(Integer[] ids) {
        studentMapper.deleteStufents(ids);
    }

    /**
     * 添加学员信息
     * @param student
     * @return
     */
    @Override
    public void addStudentData(Student student) {
        //补全基本数据
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        //调用mapper接口，添加学生信息
        studentMapper.addStudentData(student);
    }

    @Override
    public Student selectById(Integer id) {
        Student student = studentMapper.selectById(id);
        return student;
    }

    /**
     * 修改学员信息
     * @param student
     */
    @Override
    public void updateStudentData(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateStudentData(student);
    }

    /**
     * 学员违纪处理
     */
    @Override
    public void violationHandling(Integer stuId, Integer stuScore) {
        studentMapper.violationHandling(stuId,stuScore);
    }
}
