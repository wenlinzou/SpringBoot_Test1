package com.mooc.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mqliu
 * @description
 * @date 2018/8/23 10:11
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);


    @Pointcut("execution(public * com.mooc.demo.controller.GirlController.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //备注使用javax
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url = {}", request.getRequestURL());
        //method
        logger.info("method = {}", request.getMethod());
        //ip
        logger.info("ip = {}", request.getRemoteAddr());
        //类-方法
        logger.info("class_method = {}, name = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //参数
        logger.info("args = {}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("222");
    }

    /**
     * 方法出参
     *
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response = {}", object.toString());

    }

}
