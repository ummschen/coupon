package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.entity.CouponType;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.BusinessDOMapper;
import com.coupon.api.mapper.CouponTypeDOMapper;
import com.coupon.api.mapper.CouponTypeDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.Result;
import com.coupon.api.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    @Autowired
    CouponTypeDOMapper couponTypeDOMapper;
    @Autowired
    BusinessDOMapper businessDOMapper;
    @Override
    @Transactional
    public int save(CouponTypeDO couponTypeDO) {
        if(couponTypeDO!=null&& StringUtils.isNotBlank(couponTypeDO.getCode())){
            CouponTypeDO couponType = new CouponTypeDO();
            couponType.setCode(couponTypeDO.getBusinessCode());
            couponType.setName(couponTypeDO.getName());
            couponType.setPrice(couponTypeDO.getPrice());
            CouponTypeDO resultCouponType=couponTypeDOMapper.selectOne(couponType);
            if(resultCouponType!=null){
                return -2;
            }
        }
        couponTypeDO.setCreateTime(new Date());
        return couponTypeDOMapper.insertSelective(couponTypeDO);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class } )
    public Result saves(CouponTypeDO couponTypeDO) {
        int falg=1;
        if(couponTypeDO!=null&& couponTypeDO.getCouponTypes()!=null){
            for (CouponType type : couponTypeDO.getCouponTypes()) {
                if(type.getPrice()==null||type.getPrice().size()==0||StringUtils.isBlank(type.getName())) Result.ofError("券码类型保存失败");
                    for (double price :type.getPrice() ) {
                        CouponTypeDO couponType = new CouponTypeDO();
                        couponType.setEnable(1);
                        couponType.setBusinessCode(couponTypeDO.getBusinessCode());
                        couponType.setRemark(couponTypeDO.getRemark());
                        couponType.setCode(UUID.randomUUID().toString());
                        couponType.setName(type.getName());
                        couponType.setPrice(new BigDecimal(price));
                        falg=save(couponType);
                        if(falg<=0){
                            try {
                                throw new Exception("保存失败！！！");
                            } catch (Exception e) {
                                e.printStackTrace();
                                //手动开启事务回滚
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            }
                            return Result.ofError("保存失败 "+type.getName()+" 已有相同券码类型名称存在"  );
                        }
                }
            }
        }
          return  Result.ofSuccess("券码类型保存成功");
    }
    @Override
    public int update(CouponTypeDO couponTypeDO) {
        String name =couponTypeDO.getName();
        BigDecimal price =couponTypeDO.getPrice();
       if(StringUtils.isNotBlank(name)&&price!=null){
           CouponTypeDO resultCouponType=couponTypeDOMapper.selectByName(name,couponTypeDO.getId());
           if(resultCouponType!=null){
               return -couponTypeDO.getPrice().intValue();
           }
       }
        return couponTypeDOMapper.updateByPrimaryKeySelective(couponTypeDO);
    }

    @Override
    public int queryCount(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.queryCount(couponTypeDO);
    }

    @Override
    public List<CouponTypeDTO> queryList(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.queryList(couponTypeDO);
    }

    @Override
    public CouponTypeDO query(CouponTypeDO couponTypeDO) {
        return couponTypeDOMapper.selectOne(couponTypeDO);
    }
}
