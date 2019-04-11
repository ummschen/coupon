package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.BusinessDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.BusinessDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.BusinessService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessDOMapper businessDOMapper;

    @Override
    public int save(BusinessDO businessDO) {
        return businessDOMapper.insertSelective(businessDO);
    }

    @Override
    public int update(BusinessDO businessDO) {
        return businessDOMapper.updateByPrimaryKeySelective(businessDO);
    }

    @Override
    public int queryCount(BusinessDO businessDO) {
        return businessDOMapper.selectCount(businessDO);
    }

    @Override
    public List<BusinessDO> queryList(BusinessDO businessDO) {
        RowBounds  rowBounds=new RowBounds(businessDO.getPageIndex(),businessDO.getPageSize());
        return businessDOMapper.selectByRowBounds(businessDO,rowBounds);
    }
}
