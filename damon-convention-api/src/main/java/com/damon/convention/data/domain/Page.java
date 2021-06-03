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
 * 分页对象
 * @author Damon
 * @version V1.0
 * @since 2021-01-08 10:20
 */
@Data
public class Page<T> implements Serializable {

    /**
     * 第几页
     */
    private int pageNumber;
    /**
     * 每页多少条记录数
     */
    private int pageSize;
    /**
     * 总页
     */
    private int totalPages;
    /**
     * 总记录数
     */
    private long totalCount;

    /**
     * 结果集合
     */
    private List<T> results;
}
