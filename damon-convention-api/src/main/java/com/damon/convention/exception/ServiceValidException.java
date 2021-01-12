/*
 * Copyright (c) 2001-2018 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 *
 */
package com.damon.convention.exception;

import com.damon.convention.data.code.CommonCode;
import com.damon.convention.data.code.ResultCode;
import com.damon.convention.data.domain.IViolationItem;
import com.damon.convention.data.domain.ViolationItem;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * 参数校验相关异常
 *
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:28
 */
@Getter
public class ServiceValidException extends ServiceException {

    private static final long serialVersionUID = 7725721546446842718L;

    private List<IViolationItem> violationItems = new ArrayList<>();

    public ServiceValidException() {
        super(CommonCode.PARAMS_CHECK_ERROR);
    }

    public ServiceValidException(Throwable cause) {
        super(cause);

        if (cause instanceof ServiceValidException) {
            violationItems = ((ServiceValidException)cause).getViolationItems();
        }
    }

    public ServiceValidException(String message) {
        super(CommonCode.PARAMS_CHECK_ERROR.code(), message);
    }

    public ServiceValidException(String message, Throwable cause) {
        super(CommonCode.PARAMS_CHECK_ERROR.code(), message, cause);
    }

    public ServiceValidException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ServiceValidException(String code, String message, List<IViolationItem> violationItems) {
        super(code, message);
        this.violationItems = violationItems;
    }

    public ServiceValidException(List<IViolationItem> violationItems) {
        super(CommonCode.PARAMS_CHECK_ERROR.code(), CommonCode.PARAMS_CHECK_ERROR.message());
        this.violationItems = violationItems;
    }

    public ServiceValidException(ResultCode serviceCode) {
        super(serviceCode);
    }

    public List<IViolationItem> getViolationItems() {
        return violationItems;
    }

    public ServiceValidException addViolationItem(String field, String message) {
        if (this.violationItems == null) {
            this.violationItems = new ArrayList<>();
        }
        violationItems.add(new ViolationItem(field, message));
        return this;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public String toString() {
        return "ServiceValidException{" +
                "code='" + code + '\'' +
                ", message='" + super.getMessage() + '\'' +
                ", violationItem=" + violationItems +
                '}';
    }

}
