package com.coupon.api.entity;

import com.coupon.api.utils.PageInfo;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Table(name = "coupon_stat")
@Data
public class CouponStatDO extends PageInfo {
    @Id
    private Integer id;

    private String businessCode;

    private String channelCode;

    private String couponType;

    private Integer writeOff;
    @Column(name = "un_write_off")
    private Integer unWriteOff;

    private Integer couponTotal;

    private Date createTime;

    private Date updateTime;

}