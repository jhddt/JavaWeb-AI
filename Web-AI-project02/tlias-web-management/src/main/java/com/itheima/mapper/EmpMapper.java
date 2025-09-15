package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/**
 * 处理员工基本信息
 * （原始的分页查询的方法）
 */
@Mapper
public interface EmpMapper {
    //-------------------------------------------原始的分页查询的SQL语句------------------------------------
    /**
     * 查询总记录数
     */
//    @Select("select count(*) from dept,emp e left join dept d on e.dept_id = d.id" )
//    public Long count();

    /**
     * 分页查询
     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);

    //-------------------------------------------PageHelper的分页查询的SQL语句------------------------------------
    //使用pageHelper，SQL的语句结尾不能加分号
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list();

    /**
     * 条件分页查询
     *
     * @param name
     * @param gender
     * @param begin
     * @param end
     * @return
     */
    List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    //通过@Options(useGeneratedKeys = true, keyProperty = "id")注释，MyBatis 会自动将数据库生成的 id 填充到emp对象中的id属性中
    @Insert("insert into emp (username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void addEmp(Emp emp);

    /**
     * 根据ID批量删除员工的基本信息
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询员工信息以及员工工作经历信息
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工基本信息
     */
    void updateById(Emp emp);
}
