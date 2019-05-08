package com.coupon.api.service;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponStatDO;

import java.util.List;

public interface CouponStatService {
    int save(CouponStatDO couponStatDO);
    int update(CouponStatDO couponStatDO);
    int queryCount(CouponStatDO couponStatDO);
    int generate(CouponStatDO couponStatDO,int num);
    int distribute(CouponStatDO couponStatDO,int num);
    int writeOff(CouponStatDO couponStatDO);
    List<CouponStatDO> queryList(CouponStatDO couponStatDO);
}
