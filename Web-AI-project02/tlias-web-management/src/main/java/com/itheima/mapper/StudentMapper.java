package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 统计学员学历信息
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> studentDegreeData();

    /**
     * 查询所有学员信息并分页展示
     * @param name
     * @param degree
     * @param clazzId
     * @return
     */
    List<Student> getStudentPageDatas(String name, Integer degree, Integer clazzId);

    /**
     * 删除学员信息
     * @param ids
     */
    void deleteStufents(Integer[] ids);


    /**
     * 添加学员信息
     * @param student
     * @return
     */
    @Insert("INSERT INTO student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void addStudentData(Student student);

    /**
     * 根据id查询学员的信息
     * @param id
     * @return
     */
    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    Student selectById(Integer id);

    /**
     * 修改学员信息
     * @param student
     */
    void updateStudentData(Student student);

    /**
     * 违纪处理
     * @param stuId
     * @param stuScore
     */
    @Update("update student set violation_score = #{stuScore} where id = #{stuId}")
    void violationHandling(@Param("stuId") Integer stuId, @Param("stuScore") Integer stuScore);
}
