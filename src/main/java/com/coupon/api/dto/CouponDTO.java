package com.coupon.api.dto;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "coupon")
@Data
public class CouponDTO extends PageInfo {
    @Id
    @ApiModelProperty(value = "渠道名称")
    private Integer id;

    @ApiModelProperty(value = "券码")
    private String coupon;

    @ApiModelProperty(value = "券码种类")
    private String couponType;

    @ApiModelProperty(value = "金额")
    private BigDecimal price;

    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @ApiModelProperty(value = "商户编码")
    private String businessCode;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "到期时间")
    @Column(name = "endTime")
    private String endTime;

    @ApiModelProperty(value = "核销账户")
    private String writeOffAccount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "数量")
    private int  num;




}