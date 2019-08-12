package com.coupon.api.service;

import com.coupon.api.entity.CouponWriteDayDO;

import java.util.List;

public interface CouponWriteDayService {
    List<CouponWriteDayDO> queryList(CouponWriteDayDO couponWriteDayDO);
    int queryCount(CouponWriteDayDO couponWriteDayDO);

}
