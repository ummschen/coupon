package com.coupon.api.service.impl;

import com.coupon.api.entity.ManageDO;
import com.coupon.api.mapper.ManageDOMapper;
import com.coupon.api.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    ManageDOMapper manageDOMapper;
    @Override
    public ManageDO query(ManageDO manageDO) {
        return manageDOMapper.selectOne(manageDO);
    }
}
