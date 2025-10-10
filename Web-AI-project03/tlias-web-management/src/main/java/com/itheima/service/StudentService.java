package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 查询全部学员信息并分页展示
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> getStudentPageDatas(StudentQueryParam studentQueryParam);

    /**
     *删除学员信息
     * @param ids
     */
    void deleteStufents(Integer[] ids);

    /**
     * 添加学员信息
     * @param student
     * @return
     */
    void addStudentData(Student student);

    /**
     * 根据id查询学员信息
     * @param id
     * @return
     */
    Student selectById(Integer id);

    /**
     * 修改学员信息
     * @param student
     */
    void updateStudentData(Student student);

    /**
     * 学员违纪处理
     */
    void violationHandling(Integer stuId, Integer stuScore);
}
