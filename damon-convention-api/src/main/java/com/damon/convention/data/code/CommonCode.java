/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.data.code;


/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:23
 */
public enum CommonCode implements ResultCode{

    SUCCESS("0","成功"),
    UNKNOWN_ERROR("1","未知的系统异常"),
    DB_ERROR("2","数据库操作异常"),
    PARAMS_CHECK_ERROR("3","参数校验失败");


    /**
     * 响应码
     */
    private final String code;

    /**
     * 响应消息
     */
    private final String message;

    CommonCode(String code, String message) {
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
