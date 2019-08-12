package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "coupon_write_month")
@Data
public class CouponWriteMonthDO extends PageInfo {
    @Id
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "核销账户")
    private String account;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
    private Date statMonth;
    @ApiModelProperty(value = "核销数量")
    private Integer writeOffNum;
    @ApiModelProperty(value = "核销金额")
    @Column(name = "write_off_total")
    private BigDecimal writeOffTotal;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "更新时间时间")
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty(value = "商户编码")
    private  String businessCode;
    @ApiModelProperty(value = "渠道编码")
    private  String channelCode;
    @ApiModelProperty(value = "商户编码")
    private  String couponType;
    @Transient
    @ApiModelProperty(value = "券码种类名称")
    private String couponTypeName;
    @Transient
    @ApiModelProperty(value = "渠道名称")
    private String channelName;
    @Transient
    @ApiModelProperty(value = "商户名称")
    private String businessName;
    @Transient
    @ApiModelProperty(value = "开始统计日期")
    private String startStatMonth;
    @Transient
    @ApiModelProperty(value = "结束统计日期")
    private String endStatMonth;

}