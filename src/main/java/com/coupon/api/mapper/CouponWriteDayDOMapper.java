package com.coupon.api.mapper;

import com.coupon.api.entity.CouponWriteDayDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface CouponWriteDayDOMapper extends Mapper<CouponWriteDayDO> {
    void  task(@Param("startTime")String startTime,@Param("endTime") String endTime);
    List<CouponWriteDayDO> queryList(CouponWriteDayDO couponWriteDayDO) ;
    int queryCount(CouponWriteDayDO couponWriteDayDO) ;
}