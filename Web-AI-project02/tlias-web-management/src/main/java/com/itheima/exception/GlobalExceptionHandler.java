package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflection;
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


}
