package com.coupon.api.service.impl;

import com.coupon.api.entity.CouponWriteDayDO;
import com.coupon.api.mapper.CouponWriteDayDOMapper;
import com.coupon.api.service.CouponWriteDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CouponWriteDayServiceImpl implements CouponWriteDayService {
    @Autowired
    CouponWriteDayDOMapper couponWriteDayDOMapper;


    @Override
    public List<CouponWriteDayDO> queryList(CouponWriteDayDO couponWriteDayDO) {
        return couponWriteDayDOMapper.queryList(couponWriteDayDO);
    }

    @Override
    public int queryCount(CouponWriteDayDO couponWriteDayDO) {
        return couponWriteDayDOMapper.queryCount(couponWriteDayDO);
    }
}
