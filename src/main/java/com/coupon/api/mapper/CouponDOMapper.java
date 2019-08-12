package com.coupon.api.mapper;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CouponDOMapper extends Mapper<CouponDO> {
    List<CouponDTO> queryList(CouponDO couponDO);
    List<Map<String ,Object>> groupByType(CouponDO couponDO);
    int queryCount(CouponDO couponDO);
    int insertBatch( List<CouponDO> list);
    int distribute(@Param("coupon") CouponDO couponDO,@Param("num") int num);
    CouponDO selecyByCouponCode(@Param("coupon") String coupon);
    double sumPrice(CouponDO couponDO);
}