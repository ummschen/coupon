package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "account")
@Data
public class AccountDO extends PageInfo {
    @ApiModelProperty(value = "ID")
    @Id
    private Integer id;

    @ApiModelProperty(value = "账户")
    private String account;

    @Column(name = "account_name")
    @ApiModelProperty(value = "用户名称")
    private String accountName;

    @ApiModelProperty(value = "ID")
    private String password;

    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @ApiModelProperty(value = "商户编码")
    private String businessCode;

    @ApiModelProperty(value = "启用状态 0:未启用 1:启用 2:废弃")
    private Integer enable;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @Column(name = "create_time")
    @ApiModelProperty(value = "开始创建时间")
    private String startCreateTime;
    @Column(name = "create_time")
    @ApiModelProperty(value = "结束创建时间")
    private String endCreateTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}