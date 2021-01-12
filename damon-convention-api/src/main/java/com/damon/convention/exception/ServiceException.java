/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.exception;

import com.damon.convention.data.code.CommonCode;
import com.damon.convention.data.code.ResultCode;

import lombok.Getter;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:28
 */

@Getter
public class ServiceException extends RuntimeException {

    protected final String code;

    public ServiceException() {
        this.code = CommonCode.UNKNOWN_ERROR.code();
    }

    public ServiceException(Throwable cause) {
        super(cause);

        if (cause instanceof ServiceException) {
            this.code = ((ServiceException) cause).getCode();
        } else {
            this.code = CommonCode.UNKNOWN_ERROR.code();
        }
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode.code();
    }

    public ServiceException(ResultCode resultCode, Throwable cause) {
        super(resultCode.message(), cause);
        this.code = resultCode.code();
    }

    @Override
    public String toString() {
        return "ServiceException{" +
            "code='" + code + '\'' +
            "message='" + getMessage() + '\'' +
            '}';
    }
}
