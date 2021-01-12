/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.demo.exception;

import com.damon.convention.demo.exception.code.BusinessCode;
import com.damon.convention.exception.ServiceException;

/**
 * SYS_01 Exception
 *
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 16:53
 */
public class BusinessException extends ServiceException {

    public BusinessException() {
        super(BusinessCode.SYS_01);
    }

    public BusinessException(Throwable cause) {
        super(BusinessCode.SYS_01, cause);
    }

}
