package com.coupon.api.service.impl;

import com.coupon.api.entity.CouponStatDO;
import com.coupon.api.mapper.CouponStatDOMapper;
import com.coupon.api.service.CouponStatService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class CouponStatServiceImpl implements CouponStatService {
    @Autowired
    CouponStatDOMapper couponStatDOMapper;


    @Override
    public int save(CouponStatDO couponStatDO) {
        couponStatDO.setCreateTime(new Date());
        return couponStatDOMapper.insertSelective(couponStatDO);
    }

    @Override
    public int update(CouponStatDO couponStatDO) {
        return couponStatDOMapper.updateByPrimaryKeySelective(couponStatDO);
    }

    @Override
    public int queryCount(CouponStatDO couponStatDO) {
        return couponStatDOMapper.queryCount(couponStatDO);
    }

    @Override
    public List<CouponStatDO> queryList(CouponStatDO couponStatDO) {
        return couponStatDOMapper.queryList(couponStatDO);
    }

    @Override
    @Transactional
    public int generate(CouponStatDO couponStatDO, int num) {
        int flat=0;
        if(num<=0){
            return flat;
        }
        if (couponStatDO!=null){
            CouponStatDO couponStat=couponStatDOMapper.selectOne(couponStatDO);
            if(couponStat!=null){
                flat=couponStatDOMapper.generate(couponStatDO,num);
            }else {
                couponStatDO.setCouponTotal(num);
                couponStatDO.setUnWriteOff(num);
                couponStatDO.setCreateTime(new Date());
                flat=couponStatDOMapper.insertSelective(couponStatDO);
            }
        }

        return flat;
    }

    @Override
    @Transactional
    public int distribute(CouponStatDO couponStatDO, int num) {
        int flat= generate(couponStatDO,num);
        if (flat>0){
            couponStatDO.setChannelCode("");
            flat=couponStatDOMapper.distribute(couponStatDO,num);
        }
        return flat;
    }

    @Override
    @Transactional
    public int writeOff(CouponStatDO couponStatDO) {
        int flat=0;
        if (couponStatDO!=null){
            CouponStatDO couponStat=couponStatDOMapper.selectOne(couponStatDO);
            if(couponStat!=null){
                flat=couponStatDOMapper.writeOff(couponStatDO);
            }
        }
        return flat;
    }

}
