package com.coupon.api.service.impl;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.*;
import com.coupon.api.enums.CouponStatusEnum;
import com.coupon.api.mapper.AccountDOMapper;
import com.coupon.api.mapper.CouponDOMapper;
import com.coupon.api.mapper.CouponStatDOMapper;
import com.coupon.api.mapper.DistributeDOMapper;
import com.coupon.api.service.CouponService;
import com.coupon.api.service.CouponStatService;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.util.StringUtil;

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
    @Autowired
    AccountDOMapper accountDOMapper;
    @Autowired
    DistributeDOMapper distributeDOMapper;

    @Override
    @Transactional
    public int save(CouponDO couponDO) {
        couponDO.setCreateTime(new Date());
        return couponDOMapper.insertSelective(couponDO);
    }

    @Override
    @Transactional
    public int update(CouponDO couponDO) {
        return couponDOMapper.updateByPrimaryKeySelective(couponDO);
    }

    @Override
    public int queryCount(CouponDO couponDO) {
        return couponDOMapper.queryCount(couponDO);
    }


    @Override
    public List<CouponDTO> queryList(CouponDO couponDO) {
        return couponDOMapper.queryList(couponDO);
    }
    @Override
    public  List<Map<String ,Object>>  groupByType(CouponDO couponDO) {
        return couponDOMapper.groupByType(couponDO);
    }

    @Override
    public double sumPrice(CouponDO couponDO) {
        return couponDOMapper.sumPrice(couponDO);
    }


    @Override
    @Transactional
    public  int generate(CouponDTO couponDTO) {

        int flag=0;
        if (couponDTO!=null
                &&couponDTO.getNum()>0
                &&StringUtils.isNotBlank(couponDTO.getBusinessCode())
                && couponDTO.getCouponTypes()!=null){
            String businessCode = couponDTO.getBusinessCode();
            //String couponType = couponDTO.getCouponType();
            List<String> couponTypes=couponDTO.getCouponTypes();
            int  num = couponDTO.getNum();
            String endTime=DateUtil.getNowTime(couponDTO.getEndTime());


                for (String couponType : couponTypes) {
                    List<CouponDO>  list = new ArrayList<>();
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

                    if (list.size()>0&&list.size()==num){
                        flag= couponDOMapper.insertBatch(list);
                        CouponStatDO couponStatDO= new CouponStatDO();
                        couponStatDO.setBusinessCode(businessCode);
                        couponStatDO.setChannelCode("");
                        couponStatDO.setCouponType(couponType);
                        couponStatService.generate(couponStatDO,flag);
                        System.out.println(flag);
                    }else{
                        try {
                            throw new Exception("分发失败！！！");
                        } catch (Exception e) {
                            e.printStackTrace();
                            //手动开启事务回滚
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        }
                        flag=0;
                    }
                }
            }



        return flag;
    }

    @Override
    @Transactional
    public Result writeOff(CouponDO couponDO) {
        int flat=0;
        if (couponDO!=null&&StringUtils.isNotBlank(couponDO.getCoupon())
                &&StringUtils.isNotBlank(couponDO.getWriteOffAccount())
        ){
            String couponCode = couponDO.getCoupon();
            String writeOffAccount=couponDO.getWriteOffAccount();

            AccountDO accountDO = new AccountDO();
            accountDO.setAccount(writeOffAccount);
            accountDO.setEnable(1);
            AccountDO account = accountDOMapper.selectOne(accountDO);
            if(account==null)return Result.ofError("核销失败 核销人员异常！！！"  );

            CouponDO couponEntity= new CouponDO();
            couponEntity.setCoupon(couponCode);
            CouponDO coupon=couponDOMapper.selectOne(couponEntity);
            if(coupon==null) return Result.ofError("核销失败 该券码不存在！！！");
            if(!coupon.getBusinessCode().equals(account.getBusinessCode()))return Result.ofError("核销失败 券码与核销人员所属商户不一致！！！");
            if(coupon.getStatus()!=1) return Result.ofError("核销失败 该券码"+ CouponStatusEnum.getCouponStatusEnum(coupon.getStatus()).getName() +"！！！");
            if(coupon.getEndTime()!=null&&DateUtil.minusSecond(coupon.getEndTime(),new Date())<0){
                coupon.setStatus(CouponStatusEnum.Invalid.getCode());
                coupon.setWriteOffAccount(writeOffAccount);
                couponDOMapper.updateByPrimaryKeySelective(coupon);
                return Result.ofError("该券码已过期失效！！！");
            }

            String slat=coupon.getSalt();
            String encryption=coupon.getEncryption();
            boolean check=MD5Util.verify(couponCode,slat,encryption);
            if(check){
                coupon.setStatus(CouponStatusEnum.WRITEOFF.getCode());
                coupon.setWriteOffTime(new Date());
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
    @Transactional(rollbackFor = { Exception.class } )
    public Result distribute(CouponDTO couponDTO) {
        if (couponDTO!=null
                &&couponDTO.getNum()>0
                &&StringUtils.isNotBlank(couponDTO.getBusinessCode())
                &&couponDTO.getChannelCodes()!=null &&couponDTO.getCouponTypes()!=null){
            String businessCode = couponDTO.getBusinessCode();
            //String couponType = couponDTO.getCouponType();
            List<String> channelCodes = couponDTO.getChannelCodes();
            List<String> couponTypes = couponDTO.getCouponTypes();
            int num =couponDTO.getNum();

                for (String couponType : couponTypes) {
                    CouponTypeDO couponTypeDO = new CouponTypeDO();
                    couponTypeDO.setCode(couponType);
                    CouponTypeDO type=couponTypeService.query(couponTypeDO);
                    String couponTypeName="";
                    if(type!=null){
                        couponTypeName=type.getName();
                    }

                    CouponDO couponDO= new CouponDO();
                    couponDO.setBusinessCode(businessCode);
                    couponDO.setCouponType(couponType);
                    couponDO.setChannelCode("");
                    int count=couponDOMapper.selectCount(couponDO);


                    CouponStatDO couponStatDO= new CouponStatDO();
                    couponStatDO.setBusinessCode(businessCode);
                    couponStatDO.setCouponType(couponType);
                    couponStatDO.setChannelCode("");
                    Integer  unWriteOff=couponStatDOMapper.selectUnWriteOffByCouponStatDO(couponStatDO);
                    unWriteOff=unWriteOff==null?0:unWriteOff;
                    if(count>=num*channelCodes.size()&&unWriteOff>=num*channelCodes.size()){
                        int batch= distributeDOMapper.selectMaxBatch(businessCode, DateUtils.format(new Date(),DateUtils.FORMAT_SHORT));
                        String  distributeSeq =businessCode+"_"+DateUtils.format(new Date(),"yyyyMMdd")+ String.format("%0" + 7 + "d", batch+1);
                        DistributeDO distributeDO = new DistributeDO();
                        distributeDO.setBatch(batch+1);
                        distributeDO.setSqe(distributeSeq);
                        distributeDO.setNum(num*channelCodes.size());
                        distributeDO.setBusinessCode(businessCode);
                        distributeDO.setDay(new Date());
                        distributeDO.setCreateTime(new Date());
                        distributeDOMapper.insert(distributeDO);

                        couponDO.setDistributeSeq(distributeSeq);
                        for (String channelCode : channelCodes) {
                            couponStatDO= new CouponStatDO();
                            couponStatDO.setBusinessCode(businessCode);
                            couponStatDO.setCouponType(couponType);
                            couponStatDO.setChannelCode("");
                            couponDO.setChannelCode(channelCode);
                            couponDO.setDistributeTime(new Date());
                            couponDOMapper.distribute(couponDO,num);
                            couponStatDO.setChannelCode(channelCode);

                            couponStatService.distribute(couponStatDO,num);
                        }

                    }else {
                        try {
                            throw new Exception("分发失败！！！");
                        } catch (Exception e) {
                            e.printStackTrace();
                            //手动开启事务回滚
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        }
                        return Result.ofError(couponTypeName+"待分发券码低于本次分发券码总数！！！（待分发券码剩余"+unWriteOff+")");
                    }
                }

                return Result.ofSuccess("分发成功！！！");
            }




        return Result.ofError("参数异常 分发失败！！！");
    }

}
