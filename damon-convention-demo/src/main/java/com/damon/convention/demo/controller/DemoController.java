/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.damon.convention.demo.controller;

import com.damon.convention.data.domain.Result;
import com.damon.convention.demo.dto.DemoEntity;
import com.damon.convention.demo.dto.RequestEntity;
import com.damon.convention.demo.exception.BusinessException;
import com.damon.convention.demo.service.ValidateService;
import com.damon.convention.utils.Results;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 16:12
 */
@RestController
@Validated
public class DemoController {

    @Autowired
    private ValidateService validateService;

    @GetMapping("/success")
    public Result<DemoEntity> demoSuccess(){
        return Results.success(new DemoEntity("test"));
    }

    @GetMapping("/fail")
    public Result<DemoEntity> demoFail(){
        throw new BusinessException();
    }



    @GetMapping("/params-fail")
    public Result<DemoEntity> demoParamsFail(@RequestParam(required = false) @NotBlank(message = "参数消息") String param){
        return  Results.success(new DemoEntity("test"));
    }

    @GetMapping("/params-fail2")
    public Result<DemoEntity> demoParamsFail2(@RequestParam(required = false) String param){
        validateService.doSomething(param,null);
        return  Results.success(new DemoEntity("test"));
    }

    @PostMapping("/params-fail3")
    public Result<DemoEntity> demoParamsFail3(@RequestBody @Valid RequestEntity entity){
        validateService.doSomething("teset",null);
        return  Results.success(new DemoEntity("test"));
    }
}
