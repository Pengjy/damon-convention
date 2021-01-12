/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.demo.exception.code;

import com.damon.convention.data.code.ResultCode;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 11:32
 */
public enum BusinessCode implements ResultCode {

    SYS_01("SYS_01", "系统异常测试");

    /**
     * 响应码
     */
    private final String code;

    /**
     * 响应消息
     */
    private final String message;

    BusinessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
