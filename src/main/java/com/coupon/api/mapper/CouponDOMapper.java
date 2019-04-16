package com.coupon.api.mapper;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CouponDOMapper extends Mapper<CouponDO> {
    List<CouponDTO> queryList(CouponDO couponDO);
    int queryCount(CouponDO couponDO);
}