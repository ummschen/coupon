package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty(value = "券码种类")
    @Column(name = "coupon_type")
    private String couponType;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "渠道编码")
    @Column(name = "channel_code")
    private String channelCode;

    @ApiModelProperty(value = "商户编码")
    @Column(name = "business_code")
    private String businessCode;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "到期时间")
    @Column(name = "endTime")
    private Date endTime;

    @ApiModelProperty(value = "核销账户")
    @Column(name = "write_off_account")
    private String writeOffAccount;

    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private Date updateTime;


}