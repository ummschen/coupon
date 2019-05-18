package com.coupon.api.service;

import com.coupon.api.entity.OauthTokenDO;

import java.util.Date;
import java.util.List;

public interface OauthTokenService {
    int save(OauthTokenDO oauthTokenDO);
    int update(OauthTokenDO oauthTokenDO);
    int queryCount(OauthTokenDO oauthTokenDO);
    OauthTokenDO queryByToken(String token,String nowTime) ;
    //List<OauthTokenDO> queryList(OauthTokenDO oauthTokenDO);
    OauthTokenDO query(OauthTokenDO oauthTokenDO );
    String addLoginToken(String account, Date date);
}
