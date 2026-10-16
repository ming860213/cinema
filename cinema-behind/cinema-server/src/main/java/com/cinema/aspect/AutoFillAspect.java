package com.cinema.aspect;

import com.cinema.annotation.AutoFill;
import com.cinema.context.BaseContext;
import com.cinema.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

    import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static com.cinema.constant.AutoFillConstant.*;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 对于mapper里的方法进行切入
     */
    @Pointcut("execution(* com.cinema.mapper.*.*(..))&& @annotation(com.cinema.annotation.AutoFill)")
    public void autoFillPointCut() {}

    /**
     * 前置通知，在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()")
    public void AutoFill(JoinPoint joinPoint){

        MethodSignature signature=(MethodSignature) joinPoint.getSignature();//方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);//获得方法上的注解对象
        OperationType operationType = autoFill.value();//获取数据库操作类型

        Object[] args = joinPoint.getArgs();//将实体放在第一位
        if(args == null || args.length == 0){
            return;
        }

        Object entity = args[0];

        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        //反射赋值
        if(OperationType.INSERT.equals(operationType)){
            //为4个公共字段赋值
            try {
                Method setCreateTime=entity.getClass().getDeclaredMethod(SET_CREATE_TIME,LocalDateTime.class);
                Method setCreateUser=entity.getClass().getDeclaredMethod(SET_CREATE_USER,Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(SET_UPDATE_USER, Long.class);

                //反射
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else if(OperationType.UPDATE.equals(operationType)){
            //为2个公共字段赋值

            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

    }
}
