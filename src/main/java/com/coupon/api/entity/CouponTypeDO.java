package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

@Table(name = "coupon_type")
@Data
public class CouponTypeDO extends PageInfo {
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
    @Transient
    private String businessName;
    @Column(name = "price")
    @ApiModelProperty(value = "券码金额")
    private BigDecimal price;

    @Transient
    @ApiModelProperty(value = "券码金额集合")
    private  List<BigDecimal> prices;
    @Transient
    @ApiModelProperty(value = "券码类型集合")
    private List<CouponType> CouponTypes;

    @ApiModelProperty(value = "启用")
    private Integer enable;

    @ApiModelProperty(value = "备注")
    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "开始创建时间")
    @Transient
    private String startCreateTime;
    @ApiModelProperty(value = "结束创建时间")
    @Transient
    private String endCreateTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}