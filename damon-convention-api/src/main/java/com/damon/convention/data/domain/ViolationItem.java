/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.data.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 字段校验结果对象
 *
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:18
 */
@Data
@AllArgsConstructor
public class ViolationItem implements IViolationItem {

    /**
     * 字段名称
     */
    private String field;

    /**
     * 错误信息
     */
    private String message;
}
