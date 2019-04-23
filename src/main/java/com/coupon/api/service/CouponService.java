package com.coupon.api.service;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.utils.Result;

import java.util.List;

public interface CouponService {
    int save(CouponDO couponDO);
    int update(CouponDO couponDO);
    int queryCount(CouponDO couponDO);
    List<CouponDO> queryList(CouponDO couponDO);
    int generate(CouponDTO couponDTO);
    Result distribute(CouponDTO couponDTO);
}
