package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.service.AccountService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDOMapper accountDOMapper;

    @Override
    public int save(AccountDO accountDO) {
        return accountDOMapper.insertSelective(accountDO);
    }

    @Override
    public int update(AccountDO accountDO) {
        return accountDOMapper.updateByPrimaryKeySelective(accountDO);
    }

    @Override
    public int queryCount(AccountDO accountDO) {
        return accountDOMapper.queryCount(accountDO);
    }

    @Override
    public List<AccountDTO> queryList(AccountDO accountDO) {
        //RowBounds  rowBounds=new RowBounds(accountDO.getPageIndex(),accountDO.getPageSize());
        return accountDOMapper.selectList(accountDO);
    }

    @Override
    public AccountDO query(AccountDO accountDO) {
        return accountDOMapper.selectOne(accountDO);
    }
}
