package com.coupon.api.service;

import com.coupon.api.entity.OauthTokenDO;

import java.util.List;

public interface OauthTokenService {
    int save(OauthTokenDO oauthTokenDO);
    int update(OauthTokenDO oauthTokenDO);
    int queryCount(OauthTokenDO oauthTokenDO);
    List<OauthTokenDO> queryList(OauthTokenDO oauthTokenDO);
}
