package com.coupon.api.service.impl;

import com.coupon.api.entity.CouponStatDO;
import com.coupon.api.mapper.CouponStatDOMapper;
import com.coupon.api.service.CouponStatService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CouponStatServiceImpl implements CouponStatService {
    @Autowired
    CouponStatDOMapper couponStatDOMapper;


    @Override
    public int save(CouponStatDO couponStatDO) {
        return couponStatDOMapper.insertSelective(couponStatDO);
    }

    @Override
    public int update(CouponStatDO couponStatDO) {
        return couponStatDOMapper.updateByPrimaryKeySelective(couponStatDO);
    }

    @Override
    public int queryCount(CouponStatDO couponStatDO) {
        return couponStatDOMapper.selectCount(couponStatDO);
    }

    @Override
    public List<CouponStatDO> queryList(CouponStatDO couponStatDO) {
        RowBounds rowBounds=new RowBounds(couponStatDO.getPageIndex(),couponStatDO.getPageSize());
        return couponStatDOMapper.selectByRowBounds(couponStatDO,rowBounds);
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
            flat=couponStatDOMapper.distribute(couponStatDO,num);
        }
        return flat;
    }

    @Override
    public int writeOff(CouponStatDO couponStatDO, int num) {
        int flat=0;
        if(num<=0){
            return flat;
        }
        if (couponStatDO!=null){
            CouponStatDO couponStat=couponStatDOMapper.selectOne(couponStatDO);
            if(couponStat!=null){
                flat=couponStatDOMapper.writeOff(couponStatDO,num);
            }
        }
        return flat;
    }

}
