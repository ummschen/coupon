package com.coupon.api.service;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;

import java.util.List;

public interface AccountService {
    int save (AccountDO accountDO);
    int update (AccountDO accountDO);
    int queryCount (AccountDO accountDO);
    List<AccountDTO> queryList (AccountDO accountDO);
    AccountDO query (AccountDO accountDO);
}
