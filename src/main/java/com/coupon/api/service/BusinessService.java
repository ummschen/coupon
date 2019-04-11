package com.coupon.api.service;

import com.coupon.api.entity.BusinessDO;

import java.util.List;

public interface BusinessService {
    int save(BusinessDO businessDO);
    int update(BusinessDO businessDO);
    int queryCount(BusinessDO businessDO);
    List<BusinessDO> queryList(BusinessDO businessDO);
}
