package com.itheima.mapper;

import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    /**
     * 插入员工操作日志
     * @param operateLog
     */
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime})")
    void insert(OperateLog operateLog);

    /**
     * 查询全部日志信息
     * @return
     */
    @Select("select ol.*,e.name operateEmpName from operate_log ol left join emp e on ol.operate_emp_id = e.id")
    List<OperateLog> selectAllOperateLog();
}