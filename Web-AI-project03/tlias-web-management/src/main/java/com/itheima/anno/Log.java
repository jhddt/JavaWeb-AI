package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志记录注解
 * 用于标记需要进行日志记录的方法
 * 该注解作用于方法级别，运行时保留
 */
@Target(ElementType.METHOD) // 用于方法级别
@Retention(RetentionPolicy.RUNTIME) // 运行时保留
public @interface Log {
}