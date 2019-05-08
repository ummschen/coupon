package com.coupon.api.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 功能描述: 基础分页参数封装
 *
 * @Author: cqf
 * @Date: 2019/1/25 14:01
 **/
@Data
public class PageInfo implements java.io.Serializable {

    private static final long serialVersionUID = -5214737781026620938L;

    private int pageIndex = SystemConstants.DEFAULT_PAGE_INDEX;

    private int pageSize = SystemConstants.DEFAULT_PAGE_SIZE;

    private long total;

    public PageInfo() {
    }

    public PageInfo(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex == 0)
            this.pageIndex = SystemConstants.DEFAULT_PAGE_INDEX;
        else
            this.pageIndex = pageIndex;
        if (pageSize == null || pageSize == 0)
            this.pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        else
            this.pageSize = pageSize;
    }


}