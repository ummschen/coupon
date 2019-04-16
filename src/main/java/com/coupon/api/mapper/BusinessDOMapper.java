package com.coupon.api.mapper;

import com.coupon.api.dto.BusinessDTO;
import com.coupon.api.entity.BusinessDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BusinessDOMapper extends Mapper<BusinessDO> {
    List<BusinessDTO> queryList(BusinessDO accountDO);
    int queryCount(BusinessDO accountDO);
}