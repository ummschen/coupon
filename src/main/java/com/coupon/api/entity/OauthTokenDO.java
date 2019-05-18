package com.coupon.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "oauth_token")
public class OauthTokenDO  {
    @Id
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "Token")
    private String token;
    @ApiModelProperty(value = "账户编码")
    private String account;
    @ApiModelProperty(value = "到期时间")
    private Date expired;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @JsonFormat(timezone = "GMT+8", pattern ="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}