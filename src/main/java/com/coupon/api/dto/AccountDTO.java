package com.coupon.api.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO {
    private Integer id;

    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "用户名")
    private String accountName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "渠道编码")
    private String channel;
    @ApiModelProperty(value = "渠道名称")
    private String channelName;
    @ApiModelProperty(value = " 商户编码")
    private String business;
    @ApiModelProperty(value = " 商户名称")
    private String businessName;
    @ApiModelProperty(value = "启用状态 0:未启用 1:启用 2:废弃")
    private Integer enable;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "更新时间")
    private Date createTime;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "Token")
    private String token;


}
