package com.coupon.api.service.impl;

import com.coupon.api.entity.OauthTokenDO;
import com.coupon.api.mapper.OauthTokenDOMapper;
import com.coupon.api.service.OauthTokenService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OauthTokenServiceImpl implements OauthTokenService {

    @Autowired
    OauthTokenDOMapper oauthTokenDOMapper;

    @Override
    public int save(OauthTokenDO oauthTokenDO) {
        return oauthTokenDOMapper.insertSelective(oauthTokenDO);
    }

    @Override
    public int update(OauthTokenDO oauthTokenDO) {
        return oauthTokenDOMapper.updateByPrimaryKeySelective(oauthTokenDO);
    }

    @Override
    public int queryCount(OauthTokenDO oauthTokenDO) {
        return oauthTokenDOMapper.selectCount(oauthTokenDO);
    }

    @Override
    public OauthTokenDO queryByToken(String token,String nowTime) {
        OauthTokenDO oauthTokenDO= oauthTokenDOMapper.selectByToken(token,nowTime);
        return oauthTokenDO;
    }

    @Override
    public List<OauthTokenDO> queryList(OauthTokenDO oauthTokenDO) {
        RowBounds rowBounds=new RowBounds(oauthTokenDO.getPageIndex(),oauthTokenDO.getPageSize());
        return oauthTokenDOMapper.selectByRowBounds(oauthTokenDO,rowBounds);
    }

    @Override
    public OauthTokenDO query(OauthTokenDO oauthTokenDO) {
        return oauthTokenDOMapper.selectOne(oauthTokenDO);
    }
    @Override
    public String addLoginToken(String account) {
        OauthTokenDO loginTicket = new OauthTokenDO();
        loginTicket.setAccount(account);

        OauthTokenDO oauthTokenDO = query(loginTicket);

        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 2);
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setToken(UUID.randomUUID().toString().replaceAll("-", ""));

        if (oauthTokenDO != null && oauthTokenDO.getId() > 0) {
            loginTicket.setId(oauthTokenDO.getId());
            update(loginTicket);
        } else {
            save(loginTicket);
        }

        return loginTicket.getToken();
    }
}
