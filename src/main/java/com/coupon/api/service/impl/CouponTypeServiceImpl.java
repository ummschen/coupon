package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.CouponTypeDOMapper;
import com.coupon.api.mapper.CouponTypeDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    @Autowired
    CouponTypeDOMapper couponTypeDOMapper;

    @Override
    public int save(CouponTypeDO couponTypeDO) {
        if(couponTypeDO!=null&& StringUtils.isNotBlank(couponTypeDO.getCode())){
            CouponTypeDO couponType = new CouponTypeDO();
            couponType.setCode(couponTypeDO.getCode());
            CouponTypeDO resultCouponType=couponTypeDOMapper.selectOne(couponType);
            if(resultCouponType!=null){
                return -2;
            }
        }
        couponTypeDO.setCreateTime(new Date());
        return couponTypeDOMapper.insertSelective(couponTypeDO);
    }

    @Override
    public int update(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.updateByPrimaryKeySelective(couponTypeDO);
    }

    @Override
    public int queryCount(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.queryCount(couponTypeDO);
    }

    @Override
    public List<CouponTypeDTO> queryList(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.queryList(couponTypeDO);
    }

    @Override
    public CouponTypeDO query(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.selectOne(couponTypeDO);
    }
}
