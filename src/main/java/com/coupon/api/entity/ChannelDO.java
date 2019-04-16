package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "channel")
@Data
public class ChannelDO extends PageInfo {
    @Id
    @ApiModelProperty(value = "ID")
    private Integer id;

    @Column(name = "channel_code")
    @ApiModelProperty(value = "渠道编码")
    private String channelCode;

    @Column(name = "channel_name")
    @ApiModelProperty(value = "渠道名称")
    private String channelName;

    @ApiModelProperty(value = "启用状态 0:未启用 1:启用 2:废弃")
    private Integer enable;

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