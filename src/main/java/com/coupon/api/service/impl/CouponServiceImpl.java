package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.CouponDOMapper;
import com.coupon.api.mapper.CouponDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.CouponService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponDOMapper couponDOMapper;

    @Override
    public int save(CouponDO couponDO) {
        return couponDOMapper.insertSelective(couponDO);
    }

    @Override
    public int update(CouponDO couponDO) {
        return couponDOMapper.updateByPrimaryKeySelective(couponDO);
    }

    @Override
    public int queryCount(CouponDO couponDO) {
        return couponDOMapper.selectCount(couponDO);
    }

    @Override
    public List<CouponDO> queryList(CouponDO couponDO) {
        RowBounds rowBounds=new RowBounds(couponDO.getPageIndex(),couponDO.getPageSize());
        return couponDOMapper.selectByRowBounds(couponDO,rowBounds);
    }
}
