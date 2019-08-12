package com.coupon.api.mapper;

import com.coupon.api.entity.CouponWriteMonthDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface CouponWriteMonthDOMapper extends Mapper<CouponWriteMonthDO> {
    void  task(@Param("startDay")String startDay, @Param("endDay") String endDay);
    List<CouponWriteMonthDO> queryList(CouponWriteMonthDO couponWriteDayDO) ;
    int queryCount(CouponWriteMonthDO couponWriteDayDO) ;
}