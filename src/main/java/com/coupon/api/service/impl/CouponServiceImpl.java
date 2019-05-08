package com.coupon.api.service.impl;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.entity.CouponStatDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.enums.CouponStatusEnum;
import com.coupon.api.mapper.CouponDOMapper;
import com.coupon.api.mapper.CouponStatDOMapper;
import com.coupon.api.service.CouponService;
import com.coupon.api.service.CouponStatService;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.DateUtil;
import com.coupon.api.utils.MD5Util;
import com.coupon.api.utils.Result;
import com.coupon.api.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponDOMapper couponDOMapper;
    @Autowired
    CouponTypeService couponTypeService;
    @Autowired
    CouponStatService couponStatService;
    @Autowired
    CouponStatDOMapper couponStatDOMapper;

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
    public List<CouponDTO> queryList(CouponDO couponDO) {
        return couponDOMapper.queryList(couponDO);
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
            BigDecimal price =null;
            if (type!=null ) {
                 price=type.getPrice();
            }else {
                return flag;
            }
            int  num = couponDTO.getNum();
            int i = 0;
            while (i < num) {
                CouponDO couponDO = new CouponDO();
                String coupon=MD5Util.generateShortUuid();
                Map<String,String>  encryption=MD5Util.encryption(coupon);
                couponDO.setBusinessCode(businessCode);
                couponDO.setCouponType(couponType);
                couponDO.setCoupon(coupon);
                couponDO.setStatus(0);
                couponDO.setEncryption(encryption.get("encryption"));
                couponDO.setSalt(encryption.get("salt"));
                couponDO.setPrice(Double.parseDouble(price.toString()));
                couponDO.setEndTime(DateUtil.parseDate(DateUtil.DATE_TIME_PATTERN,endTime));
                list.add(couponDO);
                i++;
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
    public Result writeOff(CouponDO couponDO) {
        int flat=0;
        if (couponDO!=null&&StringUtils.isNotBlank(couponDO.getCoupon())
            &&StringUtils.isNotBlank(couponDO.getBusinessCode())
                &&StringUtils.isNotBlank(couponDO.getBusinessCode())
        ){
            String couponCode = couponDO.getCoupon();
            CouponDO couponEntity= new CouponDO();
            couponEntity.setCoupon(couponCode);
            couponEntity.setBusinessCode(couponDO.getBusinessCode());
            couponEntity.setChannelCode(couponDO.getChannelCode());
            CouponDO coupon=couponDOMapper.selectOne(couponEntity);
            if(coupon==null) return Result.ofError("核销失败 该券码不存在！！！");

            if(coupon.getStatus()!=1) return Result.ofError("核销失败 该券码"+ CouponStatusEnum.getCouponStatusEnum(coupon.getStatus()).getName() +"！！！");

            if(coupon.getEndTime()!=null&&DateUtil.minusSecond(coupon.getEndTime(),new Date())<0){
                coupon.setStatus(CouponStatusEnum.Invalid.getCode());
                couponDOMapper.updateByPrimaryKeySelective(coupon);
                return Result.ofError("该券码已过期失效！！！");
            }

            String slat=coupon.getSalt();
            String encryption=coupon.getEncryption();
            boolean check=MD5Util.verify(couponCode,slat,encryption);
            if(check){
                coupon.setStatus(CouponStatusEnum.WRITEOFF.getCode());
                coupon.setWriteOffAccount(couponDO.getWriteOffAccount());
                 flat=couponDOMapper.updateByPrimaryKeySelective(coupon);
                if(flat>0){
                    CouponStatDO couponStatDO= new CouponStatDO();
                    couponStatDO.setBusinessCode(coupon.getBusinessCode());
                    couponStatDO.setChannelCode(coupon.getChannelCode());
                    couponStatDO.setCouponType(coupon.getCouponType());
                    flat=couponStatService.writeOff(couponStatDO);

                    if(flat>0)return Result.ofSuccess("核销成功！！！");
                }
            }else {
                return Result.ofError("券码校验失败 无法核销！！！");
            }
        }
       return Result.ofError("核销失败 券码无效！！！");
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


            CouponStatDO couponStatDO= new CouponStatDO();
            couponStatDO.setBusinessCode(businessCode);
            couponStatDO.setCouponType(couponType);
            couponStatDO.setChannelCode("");
            int  unWriteOff=couponStatDOMapper.selectUnWriteOffByCouponStatDO(couponStatDO);

            if(count>=num*channelCodes.size()&&unWriteOff>=num*channelCodes.size()){
                for (String channelCode : channelCodes) {
                    couponDO.setChannelCode(channelCode);
                    couponDOMapper.distribute(couponDO,num);

                    couponStatDO.setChannelCode(channelCode);
                    couponStatService.distribute(couponStatDO,num);
                }

                return Result.ofSuccess("分发成功！！！");
            }else {
                return Result.ofError("待分发券码低于本次分发券码总数！！！");
            }
        }


        return Result.ofError("参数异常 分发失败！！！");
    }


}
