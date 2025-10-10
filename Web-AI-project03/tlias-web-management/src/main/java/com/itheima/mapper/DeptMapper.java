package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 方法一
     * @Results和@Result实现手动结果映射
     */
//    @Results({
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "update_time",property = "updateTime")
//    })
    /**
     *方法二
     * 起别名
     */
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc ")
//    List<Dept> findAll();

    /**
     * 查询所有的部门数据
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();

    /**
     * 根据ID删除部门信息
     */
    @Delete("delete from dept where id = #{id}")
    void deleteByID(Integer id);

    /**
     * 添加新的部门
     *
     */
    @Insert("insert into dept (name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insertByName(Dept dept);


    /**
     * 根据id查询部门信息
     *
     * @return
     */
    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept findById(Integer id);

    /**
     * 修改部门信息
     */
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id};")
    void update(Dept dept);
}
