package com.coupon.api.service;

import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.utils.Result;

import java.util.List;

public interface CouponTypeService {
    int save(CouponTypeDO couponTypeDO);
    Result saves(CouponTypeDO couponTypeDO);
    int update(CouponTypeDO couponTypeDO);
    int queryCount(CouponTypeDO couponTypeDO);
    List<CouponTypeDTO> queryList(CouponTypeDO couponTypeDO);
    CouponTypeDO query(CouponTypeDO couponTypeDO);
}
