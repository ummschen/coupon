package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
@Table(name = "coupon_stat")
@Data
public class CouponStatDO extends PageInfo {
    @Id
    private Integer id;

    private String businessCode;

    private String channelCode;

    private String couponType;
    @Transient
    @ApiModelProperty(value = "券码种类名称")
    private String couponTypeName;
    @Transient
    @ApiModelProperty(value = "券码种类价格")
    private BigDecimal typePrice;
    @Transient
    @ApiModelProperty(value = "渠道名称")
    private String channelName;
    @Transient
    @ApiModelProperty(value = "商户名称")
    private String businessName;

    private Integer writeOff;
    @Column(name = "un_write_off")
    private Integer unWriteOff;

    private Integer couponTotal;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}