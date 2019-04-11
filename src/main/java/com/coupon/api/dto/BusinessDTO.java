package com.coupon.api.dto;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "business")
@Data
public class BusinessDTO  {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "business_code")
    @ApiModelProperty(value = "商户编码")
    private String businessCode;

    @Column(name = "business_name")
    @ApiModelProperty(value = "商户名称")
    private String businessName;

    @ApiModelProperty(value = "启用状态 0:未启用 1:启用 2:废弃")
    private Integer enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}