package com.coupon.api.mapper;

import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.CouponTypeDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface CouponTypeDOMapper extends Mapper<CouponTypeDO> {
    List<CouponTypeDTO> queryList(CouponTypeDO couponTypeDO);
    int queryCount(CouponTypeDO couponTypeDO);
}