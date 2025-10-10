package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学员信息分页查询实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private Integer page;//页码
    private Integer pageSize;//每页数据条数
    private Integer degree;//学历
    private Integer clazzId;//班级id
    private String name;//学员姓名
}
