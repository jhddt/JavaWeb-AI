package com.itheima.mapper;

import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 处理工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量添加员工工作经历信息
     * @param exprList
     */
    void addEmpExpr(List<EmpExpr> exprList);

    /**
     * 根据员工ID批量删除员工工作经历信息
     * @param empIds
     */
    void deleteByEmpIds(List<Integer> empIds);
}
