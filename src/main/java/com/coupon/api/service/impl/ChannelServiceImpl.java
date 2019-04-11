package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.BusinessDO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.ChannelDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.ChannelService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelDOMapper channelDOMapper;

    @Override
    public int save(ChannelDO channelDO) {
        return channelDOMapper.insertSelective(channelDO);
    }

    @Override
    public int update(ChannelDO channelDO) {
        return channelDOMapper.updateByPrimaryKeySelective(channelDO);
    }

    @Override
    public int queryCount(ChannelDO channelDO) {
        return channelDOMapper.selectCount(channelDO);
    }

    @Override
    public List<ChannelDO> queryList(ChannelDO channelDO) {
        RowBounds rowBounds=new RowBounds(channelDO.getPageIndex(),channelDO.getPageSize());
        return channelDOMapper.selectByRowBounds(channelDO,rowBounds);
    }
}
