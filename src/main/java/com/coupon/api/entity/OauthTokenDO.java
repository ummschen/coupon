package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "oauth_token")
public class OauthTokenDO extends PageInfo {
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

    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}