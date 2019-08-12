package com.coupon.api.service.impl;

import com.coupon.api.entity.CouponWriteMonthDO;
import com.coupon.api.mapper.CouponWriteMonthDOMapper;
import com.coupon.api.service.CouponWriteMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CouponWriteMonthServiceImpl implements CouponWriteMonthService {
    @Autowired
    CouponWriteMonthDOMapper couponWriteMonthDOMapper;

    @Override
    public List<CouponWriteMonthDO> queryList(CouponWriteMonthDO couponWriteMonthDO) {
        return couponWriteMonthDOMapper.queryList(couponWriteMonthDO);
    }

    @Override
    public int queryCount(CouponWriteMonthDO couponWriteMonthDO) {
        return couponWriteMonthDOMapper.queryCount(couponWriteMonthDO);
    }
}
