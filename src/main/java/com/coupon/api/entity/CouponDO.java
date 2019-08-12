package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "coupon")
@Data
public class CouponDO extends PageInfo {
    @Id
    @ApiModelProperty(value = "渠道名称")
    private Integer id;

    @ApiModelProperty(value = "券码")
    private String coupon;
    @ApiModelProperty(value = "加密券码")
    private String encryption;
    @ApiModelProperty(value = "随机盐")
    private String salt;
    @ApiModelProperty(value = "券码种类")
    private String couponType;

    @ApiModelProperty(value = "金额")
    private double price;

    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @ApiModelProperty(value = "商户编码")
    @Column(name = "business_code")
    private String businessCode;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "到期时间")
    private Date endTime;

    @ApiModelProperty(value = "核销账户")
    private String writeOffAccount;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "核销时间")
    private Date writeOffTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "开始创建时间")
    @Transient
    private String startCreateTime;
    @ApiModelProperty(value = "结束创建时间")
    @Transient
    private String endCreateTime;
    @ApiModelProperty(value = "开始核销时间")
    @Transient
    private String startWriteOffTime;
    @ApiModelProperty(value = "结束核销时间")
    @Transient
    private String endWriteOffTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "分发时间")
    private Date distributeTime;
    @ApiModelProperty(value = "分发序列号")
    private String  distributeSeq;


    @Transient
    @ApiModelProperty(value = "开始分发时间")
    private String startDistributeTime;
    @Transient
    @ApiModelProperty(value = "结束分发时间")
    private String endDistributeTime;

}