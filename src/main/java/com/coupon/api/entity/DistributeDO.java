package com.coupon.api.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "distribute")
@Data
public class DistributeDO {
    @Id
    private Integer id;

    @Column(name = "business_code")
    private String businessCode;

    private String sqe;

    private Integer batch;

    private Integer num;

    private Date day;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;


}