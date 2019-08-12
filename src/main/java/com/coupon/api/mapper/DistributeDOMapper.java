package com.coupon.api.mapper;

import com.coupon.api.entity.DistributeDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface DistributeDOMapper extends Mapper<DistributeDO> {
    int selectMaxBatch(@Param("businessCode") String businessCode,@Param("day") String  day);
}