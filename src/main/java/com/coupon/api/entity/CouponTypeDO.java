package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
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

    @ApiModelProperty(value = "券码金额")
    private BigDecimal price;

    @ApiModelProperty(value = "启用")
    private String enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "开始创建时间")
    private String startCreateTime;
    @Column(name = "create_time")
    @ApiModelProperty(value = "结束创建时间")
    private String endCreateTime;
    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}