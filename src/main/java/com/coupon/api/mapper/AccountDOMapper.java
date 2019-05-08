package com.coupon.api.mapper;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface AccountDOMapper extends Mapper<AccountDO> {
    List<AccountDTO> selectList(AccountDO accountDO);
    int queryCount(AccountDO accountDO);
    @Override
    AccountDO selectOne(AccountDO accountDO);
}