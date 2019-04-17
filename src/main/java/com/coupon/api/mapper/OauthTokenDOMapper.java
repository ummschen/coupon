package com.coupon.api.mapper;

import com.coupon.api.entity.OauthTokenDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OauthTokenDOMapper extends Mapper<OauthTokenDO> {
    OauthTokenDO selectByToken(@Param("token") String token,
                               @Param("nowTime") String nowTime
                                );
}