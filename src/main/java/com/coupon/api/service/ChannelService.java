package com.coupon.api.service;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.ChannelDO;

import java.util.List;

public interface ChannelService {
    int save(ChannelDO channelDO);
    int update(ChannelDO channelDO);
    int queryCount(ChannelDO channelDO);
    List<ChannelDO> queryList(ChannelDO channelDO);
}
