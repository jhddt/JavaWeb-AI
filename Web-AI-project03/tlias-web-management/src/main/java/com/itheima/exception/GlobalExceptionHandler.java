package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice//声明为全局异常处理器
public class GlobalExceptionHandler {
    @ExceptionHandler//声明当前为异常处理的方法
    public Result handleException(Exception e){
        log.error("程序出错了~",e);
        return Result.error("出错了，请联系管理员~");
    }

     /**
     * 处理数据库重复键异常
     * 当违反数据库唯一性约束时（如重复的用户名），该方法会捕获异常并返回友好的错误信息
     * @param e DuplicateKeyException异常对象
     * @return 包含具体重复值信息的Result对象
     */
    @ExceptionHandler
    public Result handleDuplicatekeyException(DuplicateKeyException e){
        log.error("程序出错了~",e);
        // 从异常信息中提取重复条目相关信息
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        // 返回格式化的错误信息，提示用户哪个值已经存在
        return Result.error(arr[2] + "已经存在");
    }

    /**
     * 处理外键约束异常
     * 当违反数据库外键约束时（如删除被引用的记录或插入不存在的外键值），该方法会捕获异常并返回友好的错误信息
     * @param e SQLIntegrityConstraintViolationException异常对象
     * @return 包含外键约束错误信息的Result对象
     */
    @ExceptionHandler
    public Result handleForeignKeyConstraintException(DataIntegrityViolationException e) {
        log.error("外键约束异常：", e);
        String message = e.getMessage();
        // 检查是否是外键约束异常
        if (message.contains("foreign key constraint fails")) {
            return Result.error("对不起，当前部门下有员工，不能直接删除！");
        }
        // 默认外键错误信息
        return Result.error("操作失败：违反了数据完整性约束");
    }

    /**
     * 处理数字格式异常
     * 当输入的分数不是有效数字或超出范围时，该方法会捕获异常并返回友好的错误信息
     * @param e NumberFormatException异常对象
     * @return 包含"超过最大分数"信息的Result对象
     */
    @ExceptionHandler
    public Result handleNumberFormatException(NumberFormatException e) {
        log.error("数字格式异常：", e);
        return Result.error("超过最大分数");
    }

}
