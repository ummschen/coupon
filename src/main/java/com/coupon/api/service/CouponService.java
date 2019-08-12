package com.coupon.api.service;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.utils.Result;

import java.util.List;
import java.util.Map;

public interface CouponService {
    int save(CouponDO couponDO);
    int update(CouponDO couponDO);
    int queryCount(CouponDO couponDO);
    List<CouponDTO> queryList(CouponDO couponDO);
    List<Map<String ,Object>> groupByType(CouponDO couponDO);
    double sumPrice(CouponDO couponDO);
    int generate(CouponDTO couponDTO);
    Result writeOff(CouponDO couponDO);
    Result distribute(CouponDTO couponDTO);
}
