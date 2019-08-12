package com.coupon.api.utils;

import lombok.Data;

import javax.persistence.Transient;

/**
 * 功能描述: 基础分页参数封装
 *
 * @Author: cqf
 * @Date: 2019/1/25 14:01
 **/
@Data
public class PageInfo implements java.io.Serializable {

    private static final long serialVersionUID = -5214737781026620938L;
    @Transient
    private Integer pageIndex = SystemConstants.DEFAULT_PAGE_INDEX;
    @Transient
    private Integer pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
    @Transient
    private Integer offset = SystemConstants.DEFAULT_OFFSET;

    private long total;

    public PageInfo() {
    }

    public PageInfo(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex == 0)
            this.pageIndex = SystemConstants.DEFAULT_PAGE_INDEX;
        else{
            this.pageIndex = pageIndex;


        }
        if (pageSize == null || pageSize == 0)
            this.pageSize = SystemConstants.DEFAULT_PAGE_SIZE;
        else
            this.pageSize = pageSize;



    }

    public Integer getOffset() {
        if(this.pageIndex>1){
            return (this.pageIndex-1)*this.pageSize;
        }else return 0;
    }
}