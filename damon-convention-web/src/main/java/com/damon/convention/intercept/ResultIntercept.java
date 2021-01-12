/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.intercept;

import com.damon.convention.data.code.CommonCode;
import com.damon.convention.exception.ServiceException;
import com.damon.convention.utils.Results;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 14:23
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE + 18)
@Slf4j
public class ResultIntercept {

    @Pointcut("execution(com.damon.convention.data.domain.Result+ *..*.*(..))")
    private void anyResult() {
        //doSomething
    }

    @Around("anyResult()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ServiceException ex) {
            log.error("服务异常", ex);
            return Results.failure(ex);
        } catch (Exception ex) {
            log.error("系统异常", ex);
            return Results.failure(CommonCode.UNKNOWN_ERROR);
        }
    }
}
