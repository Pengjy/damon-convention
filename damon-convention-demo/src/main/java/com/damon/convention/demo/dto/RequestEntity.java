/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.demo.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-12 14:57
 */
@Data
@Validated
public class RequestEntity {

    @NotBlank(message = "key不能为空")
    private String key1;

    private String key2;
}
