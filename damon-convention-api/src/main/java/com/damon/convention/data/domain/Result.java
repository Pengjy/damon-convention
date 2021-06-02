/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.data.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 通用返回结果对象
 *
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:16
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 返回编码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回的结果对象
     */
    private T data;

    /**
     * 参数校验错误信息
     */
    private List<IViolationItem> violationItems;

}
