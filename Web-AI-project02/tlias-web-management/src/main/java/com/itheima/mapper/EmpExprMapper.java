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
    void addEmpExpr(List<EmpExpr> exprList);
}
