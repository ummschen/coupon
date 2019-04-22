package com.coupon.api.mapper;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface CouponDOMapper extends Mapper<CouponDO> {
    List<CouponDTO> queryList(CouponDO couponDO);
    int queryCount(CouponDO couponDO);
    int insertBatch( List<CouponDO> list);
}