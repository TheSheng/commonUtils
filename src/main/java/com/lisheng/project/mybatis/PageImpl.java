package com.lisheng.project.mybatis;


import com.github.pagehelper.PageHelper;
import org.apache.commons.collections4.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public final class PageImpl  {
    @Pointcut("@annotation(com.lisheng.project.mybatis.Page)")
    public void  cut(){
    }

    @Around(value = "cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] argsBefore = joinPoint.getArgs();
        String content=null;
        if(argsBefore[0] instanceof Map){
            Map map=(Map)argsBefore[0];
            Integer pageNum = MapUtils.getInteger(map, "pageNum");
            Integer pageSize = MapUtils.getInteger(map, "pageSize");
            if(null==pageNum||null==pageSize){
                throw new Exception("错误的分页参数");
            }
            PageHelper.startPage(pageNum,pageSize);
        }
           Object rs = joinPoint.proceed();
           return rs;

    }
}
