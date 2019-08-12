package com.coupon.api.dto;


import com.coupon.api.utils.PageInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AccountDTO extends PageInfo {
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
    @ApiModelProperty(value = "负责人")
    private String leader;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "启用状态 0:未启用 1:启用 2:废弃")
    private Integer enable;
    @ApiModelProperty(value = "备注")
    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    @ApiModelProperty(value = "Token")
    private String token;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "Token")
    private Date tokenEndTime;
    @ApiModelProperty(value = "开始创建时间")
    private String startCreateTime;
    @ApiModelProperty(value = "结束创建时间")
    private String endCreateTime;

}
