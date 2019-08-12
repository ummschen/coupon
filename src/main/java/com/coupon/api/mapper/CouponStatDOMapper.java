package com.coupon.api.mapper;

import com.coupon.api.entity.CouponStatDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CouponStatDOMapper extends Mapper<CouponStatDO> {
    int  generate(@Param("couponStat") CouponStatDO couponStat,@Param("num") int num);
    int  writeOff(@Param("couponStat")CouponStatDO couponStat);
    int  distribute(@Param("couponStat")CouponStatDO couponStat,@Param("num")int num);
    Integer selectUnWriteOffByCouponStatDO(@Param("couponStat")CouponStatDO couponStat);
    List<CouponStatDO> queryList(CouponStatDO couponStatDO);
    int queryCount(CouponStatDO couponStat);
}