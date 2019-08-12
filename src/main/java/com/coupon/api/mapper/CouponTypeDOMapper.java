package com.coupon.api.mapper;

import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.CouponTypeDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface CouponTypeDOMapper extends Mapper<CouponTypeDO> {
    List<CouponTypeDTO> queryList(CouponTypeDO couponTypeDO);
    int queryCount(CouponTypeDO couponTypeDO);
    CouponTypeDO selectByPrice(@Param("price") BigDecimal price, @Param("businessCode")String businessCode,@Param("name")String name, @Param("id") Integer id);
    CouponTypeDO selectByName(@Param("name") String name, @Param("id") Integer id);
}