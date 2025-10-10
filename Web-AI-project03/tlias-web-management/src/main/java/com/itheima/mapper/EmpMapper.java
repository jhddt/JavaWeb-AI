package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


/**
 * 处理员工基本信息
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


    /**
     * 添加员工信息
     * 将员工信息插入到emp表中，并将数据库生成的id自动填充到emp对象的id属性中
     *
     * @param emp 员工信息对象，包含员工的详细信息
     *           username: 用户名
     *           name: 姓名
     *           gender: 性别
     *           phone: 手机号
     *           job: 职位
     *           salary: 薪资
     *           image: 头像图片地址
     *           entryDate: 入职日期
     *           deptId: 部门ID
     *           createTime: 创建时间
     *           updateTime: 更新时间
     */
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

    /**
     * 统计员工职位人数数据
     * mapper需要传回两个参数（key职位：value人数）
     */
    @MapKey("pos")//不加也不影响
    List<Map<String,Object>> countJobEmpData();

    /**
     * 统计员工性别人数信息
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询所有员工信息
     */
    List<Emp> selectAll();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
