package com.coupon.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class ManageDTO {
    @Id
    private Integer id;
    @ApiModelProperty(value = "账号")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "启用状态 1：启用 0 关闭")
    private Integer enable;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "Token")
    private String token;



}