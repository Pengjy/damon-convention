/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.advice;

import com.damon.convention.data.domain.IViolationItem;
import com.damon.convention.data.code.CommonCode;
import com.damon.convention.data.domain.Result;
import com.damon.convention.data.domain.ViolationItem;
import com.damon.convention.utils.Results;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-12 15:09
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public Result methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
        List<FieldError> errorList = ex.getBindingResult().getFieldErrors();
        List<IViolationItem> violationItems = errorList.stream()
            .map(error -> new ViolationItem(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());

        Result result = Results.failure(CommonCode.PARAMS_CHECK_ERROR);
        result.setViolationItems(violationItems);
        return result;
    }
}
