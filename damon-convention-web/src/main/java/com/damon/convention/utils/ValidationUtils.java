/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.utils;

import com.damon.convention.data.domain.IViolationItem;
import com.damon.convention.data.domain.ViolationItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.CollectionUtils;

/**
 * 字段校验结果对象
 *
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:18
 */
public class ValidationUtils {

    private ValidationUtils() {
        //doSomething
    }

    public static List<IViolationItem> convertToResultViolationItems(
        Set<? extends ConstraintViolation<?>> constraintViolations) {
        return convertToResultViolationItems(constraintViolations, null, null);
    }

    public static List<IViolationItem> convertToResultViolationItems(
        Set<? extends ConstraintViolation<?>> constraintViolations, ParameterNameDiscoverer parameterNameDiscoverer,
        Method method) {
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return Collections.emptyList();
        }
        List<IViolationItem> violationItems = new ArrayList<>(constraintViolations.size());
        constraintViolations.forEach(constraintViolation -> {
            String fieldName = getFieldName(parameterNameDiscoverer, method, constraintViolation);
            violationItems.add(new ViolationItem(fieldName, constraintViolation.getMessage()));
        });
        return violationItems;
    }

    private static String getFieldName(ParameterNameDiscoverer parameterNameDiscoverer, Method method,
        ConstraintViolation<?> constraintViolation) {
        AtomicReference<String> fieldName = new AtomicReference<>("");
        constraintViolation.getPropertyPath().forEach(node -> {
            if (node.getKind() == ElementKind.PARAMETER) {
                fieldName.set(getParameterName(node, method, parameterNameDiscoverer));
            } else if (node.getKind() == ElementKind.PROPERTY || node.getKind() == ElementKind.RETURN_VALUE) {
                fieldName.set(node.getName());
            }
        });
        return fieldName.get();
    }

    private static String getParameterName(Path.Node node, Method method,
        ParameterNameDiscoverer parameterNameDiscoverer) {
        if (null == parameterNameDiscoverer || null == method) {
            return node.getName();
        } else {
            int index = node.as(Path.ParameterNode.class).getParameterIndex();
            String[] names = parameterNameDiscoverer.getParameterNames(method);
            return null == names ? node.getName() : names[index];
        }
    }
}
