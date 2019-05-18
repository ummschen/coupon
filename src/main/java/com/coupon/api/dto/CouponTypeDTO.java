package com.coupon.api.dto;

import com.coupon.api.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "coupon_type")
@Data
public class CouponTypeDTO extends PageInfo {
    @Id
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "券码类型编码")
    private String code;

    @ApiModelProperty(value = "券码类型名称")
    private String name;
    @ApiModelProperty(value = "商户编码")
    private String businessCode;
    @ApiModelProperty(value = "商户名称")
    private String businessName;
    @ApiModelProperty(value = "券码金额")
    private BigDecimal price;

    @ApiModelProperty(value = "启用")
    private String enable;

    @ApiModelProperty(value = "备注")
    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}