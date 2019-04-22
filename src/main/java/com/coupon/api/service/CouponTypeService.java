package com.coupon.api.service;

import com.coupon.api.entity.CouponTypeDO;

import java.util.List;

public interface CouponTypeService {
    int save(CouponTypeDO couponTypeDO);
    int update(CouponTypeDO couponTypeDO);
    int queryCount(CouponTypeDO couponTypeDO);
    List<CouponTypeDO> queryList(CouponTypeDO couponTypeDO);
    CouponTypeDO query(CouponTypeDO couponTypeDO);
}
