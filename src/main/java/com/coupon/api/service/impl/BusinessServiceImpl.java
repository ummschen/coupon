package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.BusinessDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.BusinessDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.BusinessService;
import com.coupon.api.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    BusinessDOMapper businessDOMapper;

    @Override
    public int save(BusinessDO businessDO) {
        if(businessDO!=null&& StringUtils.isNotBlank(businessDO.getBusinessCode())){
            BusinessDO business = new BusinessDO();
            business.setBusinessCode(businessDO.getBusinessCode());
            BusinessDO resultBusiness=businessDOMapper.selectOne(business);
            if(resultBusiness!=null){
                return -2;
            }
        }
        businessDO.setCreateTime(new Date());
        return businessDOMapper.insertSelective(businessDO);
    }

    @Override
    public int update(BusinessDO businessDO) {
        return businessDOMapper.updateByPrimaryKeySelective(businessDO);
    }

    @Override
    public int queryCount(BusinessDO businessDO) {
        return businessDOMapper.queryCount(businessDO);
    }

    @Override
    public List<BusinessDO> queryList(BusinessDO businessDO) {
        return businessDOMapper.queryList(businessDO);
    }
}
