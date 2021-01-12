/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.intercept;

import com.damon.convention.utils.ValidationUtils;
import com.damon.convention.exception.ServiceValidException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.ClassUtils;
import org.springframework.validation.annotation.Validated;


/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 14:23
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE + 19)
public class MethodValidationAspect {

    private final Validator validator;

    private final ParameterNameDiscoverer parameterNameDiscoverer;

    @Pointcut("@within(org.springframework.validation.annotation.Validated)")
    private void anyValidatedAnnotation() {
        //doSomething
    }

    @Pointcut("@within(javax.validation.executable.ValidateOnExecution)")
    private void anyValidateOnExecutionAnnotation() {
        //doSomething
    }

    public MethodValidationAspect() {
        parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Around("anyValidatedAnnotation() || anyValidateOnExecutionAnnotation()")
    protected Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        Class<?>[] groups = determineValidationGroups(method);

        Set<? extends ConstraintViolation<?>> results;
        try {
            results = getExecutableValidator()
                    .validateParameters(joinPoint.getThis(), method, joinPoint.getArgs(),
                            groups);
        } catch (IllegalArgumentException ex) {
            method = BridgeMethodResolver.findBridgedMethod(
                    ClassUtils.getMostSpecificMethod(method, joinPoint.getTarget().getClass()));

            results = getExecutableValidator()
                    .validateParameters(joinPoint.getThis(), method, joinPoint.getArgs(),
                            groups);
        }

        if (!results.isEmpty()) {
            throw new ServiceValidException(
                    ValidationUtils
                    .convertToResultViolationItems(results,
                            parameterNameDiscoverer, method));
        }
        return joinPoint.proceed();
    }

    private ExecutableValidator getExecutableValidator() {
        return validator.forExecutables();
    }

    private Class<?>[] determineValidationGroups(Method method) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(Default.class);
        Validated vAnn =
                AnnotationUtils.findAnnotation(method, Validated.class);
        if (vAnn != null) {
            groups.addAll(Arrays.asList(vAnn.value()));
        } else {
            vAnn = AnnotationUtils.findAnnotation(method.getDeclaringClass(), Validated.class);
            if (vAnn != null) {
                groups.addAll(Arrays.asList(vAnn.value()));
            }
        }
        return groups.toArray(new Class<?>[groups.size()]);
    }

}
