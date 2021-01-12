/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.data.domain;

import java.io.Serializable;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 15:21
 */
public interface IViolationItem extends Serializable {
    /**
     * 获取验证失败的字段名
     * @return 验证失败的字段名
     */
    String getField();

    /**
     * 设置验证失败的字段名
     * @param field 验证失败的字段名
     */
    void setField(String field);

    /**
     * 获取验证失败的信息
     * @return 验证失败的信息
     */
    String getMessage();

    /**
     * 设置验证失败的信息
     * @param message 验证失败的信息
     */
    void setMessage(String message);
}
