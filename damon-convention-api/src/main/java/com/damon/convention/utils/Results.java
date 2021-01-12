/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.utils;

import com.damon.convention.data.code.CommonCode;
import com.damon.convention.exception.ServiceException;
import com.damon.convention.data.domain.Result;
import com.damon.convention.exception.ServiceValidException;

import java.io.Serializable;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 14:43
 */
public class Results {

    private Results(){
        //doSomething
    }

    public static  <T extends Serializable>  Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(CommonCode.SUCCESS.code());
        result.setMessage(CommonCode.SUCCESS.message());
        return result;
    }

    public static <T extends Serializable> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(CommonCode.SUCCESS.code());
        result.setMessage(CommonCode.SUCCESS.message());
        result.setData(data);
        return result;
    }

    public static <T extends Serializable> Result<T> failure(CommonCode commonCode) {
        Result<T> result = new Result<>();
        result.setCode(commonCode.code());
        result.setMessage(commonCode.message());
        return result;
    }

    public static <T extends Serializable> Result<T> failure(ServiceException serviceException) {
        Result<T> result = new Result<>();
        result.setCode(serviceException.getCode());
        result.setMessage(serviceException.getMessage());

        if(serviceException instanceof ServiceValidException){
            result.setViolationItems(((ServiceValidException) serviceException).getViolationItems());
        }

        return result;
    }

}
