package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {

    /**
     *分页查询全部班级信息
     */
    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 查询所有班级信息
     */
    List<Clazz> selectAll();

    /**
     * 新增班级数据
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void addClazzData(Clazz clazz);

    /**
     * 修改班级信息
     */
    void updateClazzData(Clazz clazz);

    /**
     * 根据id删除班级信息
     * @param id
     */
    @Update("delete from clazz where id = #{id};")
    void deleteClazzData(Integer id);

    /**
     * 统计每个班级的学员人数信息
     */
    @MapKey("clazzName")
    List<Map<String, Object>> studentCountData();
}
