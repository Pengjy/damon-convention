/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.demo.service;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-11 11:18
 */
@Validated
@Service
public class ValidateService {


    public void doSomething(@NotBlank(message = "服务参数异常1") String parm1,@NotBlank(message = "服务参数异常1") String parm2){
        //doSomething
    }

}
