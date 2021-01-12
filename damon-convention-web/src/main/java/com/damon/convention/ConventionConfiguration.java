/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention;

import com.damon.convention.advice.ControllerExceptionHandler;
import com.damon.convention.intercept.MethodValidationAspect;
import com.damon.convention.intercept.ResultIntercept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 15:17
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConventionConfiguration {

    @Bean
    public ResultIntercept resultIntercept() {
        return new ResultIntercept();
    }

    @Bean
    public MethodValidationAspect methodValidationAspect(){
        return new MethodValidationAspect();
    }

    @Bean
    public ControllerExceptionHandler controllerExceptionHandler(){
        return new ControllerExceptionHandler();
    }

}
