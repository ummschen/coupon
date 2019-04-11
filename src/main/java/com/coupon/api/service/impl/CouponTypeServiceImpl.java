package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.CouponTypeDOMapper;
import com.coupon.api.mapper.CouponTypeDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.CouponTypeService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    @Autowired
    CouponTypeDOMapper couponTypeDOMapper;

    @Override
    public int save(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.insertSelective(couponTypeDO);
    }

    @Override
    public int update(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.updateByPrimaryKeySelective(couponTypeDO);
    }

    @Override
    public int queryCount(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.selectCount(couponTypeDO);
    }

    @Override
    public List<CouponTypeDO> queryList(CouponTypeDO couponTypeDO) {
        RowBounds rowBounds=new RowBounds(couponTypeDO.getPageIndex(),couponTypeDO.getPageSize());
        return couponTypeDOMapper.selectByRowBounds(couponTypeDO,rowBounds);
    }
}
