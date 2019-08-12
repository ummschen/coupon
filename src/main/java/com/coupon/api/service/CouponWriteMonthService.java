package com.coupon.api.service;

import com.coupon.api.entity.CouponWriteMonthDO;

import java.util.List;

public interface CouponWriteMonthService {
    List<CouponWriteMonthDO> queryList(CouponWriteMonthDO couponWriteMonthDO);
    int queryCount(CouponWriteMonthDO couponWriteMonthDO);
}
