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
import java.util.List;

@Table(name = "coupon")
@Data
public class CouponDTO extends PageInfo {
    @Id
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "券码")
    private String coupon;

    @ApiModelProperty(value = "券码种类")
    private String couponType;

    @ApiModelProperty(value = "券码种类名称")
    private String couponTypeName;

    @ApiModelProperty(value = "金额")
    private double price;

    @ApiModelProperty(value = "渠道编码")
    private String channelCode;
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "渠道编码数组")
    private List<String> channelCodes;
    @ApiModelProperty(value = "券码种类数组")
    private List<String> couponTypes;

    @ApiModelProperty(value = "商户编码")
    private String businessCode;
    @ApiModelProperty(value = "商户名称")
    private String businessName;

    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    @ApiModelProperty(value = "到期时间")
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "核销账户")
    private String writeOffAccount;
    @ApiModelProperty(value = "核销账户名称")
    private String writeOffAccountName;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "核销时间")
    private Date writeOffTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "数量")
    private Integer  num;

    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "分发时间")
    private Date distributeTime;
    @ApiModelProperty(value = "分发序列号")
    private String  distributeSeq;



}