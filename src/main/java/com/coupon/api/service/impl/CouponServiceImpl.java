package com.coupon.api.service.impl;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.entity.CouponStatDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.CouponDOMapper;
import com.coupon.api.mapper.CouponDOMapper;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.CouponService;
import com.coupon.api.service.CouponStatService;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.DateUtil;
import com.coupon.api.utils.MD5Util;
import com.coupon.api.utils.Result;
import com.coupon.api.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponDOMapper couponDOMapper;
    @Autowired
    CouponTypeService couponTypeService;
    @Autowired
    CouponStatService couponStatService;


    @Override
    public int save(CouponDO couponDO) {
        return couponDOMapper.insertSelective(couponDO);
    }

    @Override
    public int update(CouponDO couponDO) {
        return couponDOMapper.updateByPrimaryKeySelective(couponDO);
    }

    @Override
    public int queryCount(CouponDO couponDO) {
        return couponDOMapper.selectCount(couponDO);
    }

    @Override
    public List<CouponDO> queryList(CouponDO couponDO) {
        RowBounds rowBounds=new RowBounds(couponDO.getPageIndex(),couponDO.getPageSize());
        return couponDOMapper.selectByRowBounds(couponDO,rowBounds);
    }

    @Override
    @Transactional
    public  int generate(CouponDTO couponDTO) {
        List<CouponDO>  list = new ArrayList<>();
        int flag=0;
        if (couponDTO!=null
                &&couponDTO.getNum()>0
                &&StringUtils.isNotBlank(couponDTO.getBusinessCode())
                && StringUtils.isNotBlank(couponDTO.getCouponType())){
            String businessCode = couponDTO.getBusinessCode();
            String couponType = couponDTO.getCouponType();
            String endTime=couponDTO.getEndTime();

            CouponTypeDO couponTypeDO = new CouponTypeDO();
            couponTypeDO.setCode(couponType);
            couponTypeDO.setEnable(1);
            CouponTypeDO type=couponTypeService.query(couponTypeDO);
            double price=type.getPrice();
            int  num = couponDTO.getNum();
            int i = 0;
            while (i < num) {
                CouponDO couponDO = new CouponDO();
                String coupon=MD5Util.generateShortUuid();
                Map<String,String>  encryption=MD5Util.encryption(coupon);
                couponDO.setBusinessCode(businessCode);
                couponDO.setCouponType(couponType);
                couponDO.setCoupon(coupon);
                couponDO.setEncryption(encryption.get("encryption"));
                couponDO.setSalt(encryption.get("salt"));
                couponDO.setPrice(price);
                couponDO.setEndTime(DateUtil.parseDate(DateUtil.DATE_TIME_PATTERN,endTime));
                list.add(couponDO);
            }

            if (list.size()>0&&list.size()==couponDTO.getNum()){
                flag= couponDOMapper.insertBatch(list);
                CouponStatDO couponStatDO= new CouponStatDO();
                couponStatDO.setBusinessCode(businessCode);
                couponStatDO.setChannelCode("");
                couponStatDO.setCouponType(couponType);
                couponStatService.generate(couponStatDO,flag);
                System.out.println(flag);
            }
        }


        return flag;
    }

    @Override
    public Result distribute(CouponDTO couponDTO) {
        if (couponDTO!=null
                &&couponDTO.getNum()>0
                &&StringUtils.isNotBlank(couponDTO.getBusinessCode())
                &&StringUtils.isNotBlank(couponDTO.getCouponType())
                &&couponDTO.getChannelCodes()!=null){
            String businessCode = couponDTO.getBusinessCode();
            String couponType = couponDTO.getCouponType();
            List<String> channelCodes = couponDTO.getChannelCodes();
            int num =couponDTO.getNum();

            CouponDO couponDO= new CouponDO();
            couponDO.setBusinessCode(businessCode);
            couponDO.setCouponType(couponType);
            couponDO.setChannelCode("");

            int count=couponDOMapper.selectCount(couponDO);
            if(count>=num*channelCodes.size()){
                for (String channelCode : channelCodes) {
                    couponDO.setChannelCode(channelCode);
                    couponDOMapper.distribute(couponDO,num);

                    CouponStatDO couponStatDO= new CouponStatDO();
                    couponStatDO.setBusinessCode(businessCode);
                    couponStatDO.setChannelCode(channelCode);
                    couponStatDO.setCouponType(couponType);
                    couponStatService.distribute(couponStatDO,num);
                }
            }else {
                return Result.ofError("待分发券码低于本次分发券码总数！！！");
            }
        }


        return null;
    }
}
