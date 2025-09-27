package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.CurrentHolder;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)") // 定义切点，指定方法级别
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 获取目标类名和方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        // 获取方法参数
        String methodParams = Arrays.toString(joinPoint.getArgs());

        //方法1
        // 获取操作人ID（从JWT中解析）
//        Integer operateEmpId = null;
//        try {
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (attributes != null) {
//                HttpServletRequest request = attributes.getRequest();
//                String jwt = request.getHeader("token");
//                if (jwt != null) {
//                    Claims claims = JwtUtils.parseJWT(jwt);
//                    operateEmpId = (Integer) claims.get("id");
//                }
//            }
//        } catch (Exception e) {
//            log.warn("获取操作人ID失败: {}", e.getMessage());
//        }

        //方法2
        //从ThreadLocal中获取员工的ID
        Integer operateEmpId = CurrentHolder.getCurrentId();

        Object result = null;
        
        // 执行目标方法
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            // 记录结束时间
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 创建操作日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(result.toString());
            operateLog.setCostTime(costTime);

            // 保存日志到数据库
            try {
                operateLogMapper.insert(operateLog);
            } catch (Exception e) {
                log.error("保存操作日志失败: {}", e.getMessage());
            }
        }
    }
}