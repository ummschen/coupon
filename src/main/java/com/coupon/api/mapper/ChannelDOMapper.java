package com.coupon.api.mapper;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.ChannelDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.ChannelDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ChannelDOMapper extends Mapper<ChannelDO> {
    List<ChannelDTO> queryList(ChannelDO channelDO);
    int queryCount(ChannelDO channelDO);
}