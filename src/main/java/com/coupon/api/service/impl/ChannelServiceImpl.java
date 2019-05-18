package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.BusinessDO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.ChannelDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.ChannelService;
import com.coupon.api.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelDOMapper channelDOMapper;

    @Override
    public int save(ChannelDO channelDO) {
        if(channelDO!=null&& StringUtils.isNotBlank(channelDO.getChannelCode())){
            ChannelDO channel = new ChannelDO();
            channel.setChannelCode(channelDO.getChannelCode());
            ChannelDO resultChannel=channelDOMapper.selectOne(channel);
            if(resultChannel!=null){
                return -2;
            }
        }
        channelDO.setCreateTime(new Date());
        return channelDOMapper.insertSelective(channelDO);
    }

    @Override
    public int update(ChannelDO channelDO) {
        return channelDOMapper.updateByPrimaryKeySelective(channelDO);
    }

    @Override
    public int queryCount(ChannelDO channelDO) {
        return channelDOMapper.queryCount(channelDO);
    }

    @Override
    public List<ChannelDO> queryList(ChannelDO channelDO) {
        return channelDOMapper.queryList(channelDO);
    }
}
