package com.coupon.api.controller;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.ChannelDTO;
import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.service.CouponService;
import com.coupon.api.utils.CopyUtil;
import com.coupon.api.utils.PagingModel;
import com.coupon.api.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon/coupon")
@Api(tags={"券码管理"})
public class CouponController {
    @Autowired
    CouponService couponService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public Result list(@RequestBody CouponDO couponDO){
        PagingModel<CouponDTO> CouponDOPagingModel = new PagingModel<>();
        List<CouponDO> couponList=couponService.queryList(couponDO);
        List<CouponDTO> couponDTOList= new ArrayList<>();
        for (CouponDO coupon : couponList) {
            couponDTOList.add(CopyUtil.copy(coupon, CouponDTO.class));
        }
        int total =couponService.queryCount(couponDO);
        CouponDOPagingModel.setResults(couponDTOList);
        CouponDOPagingModel.setTotal(total);
        CouponDOPagingModel.setPageIndex(couponDO.getPageIndex());
        CouponDOPagingModel.setPageSize(couponDO.getPageSize());
        return  Result.ofSuccess(CouponDOPagingModel);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public Result save(@RequestBody CouponDO couponDO){
        int  falg=couponService.save(couponDO);
        if(falg>0){
            return  Result.ofSuccess("券码保存成功");
        }
        return  Result.ofError("券码保存失败");
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody CouponDO couponDO){
        if (couponDO==null||couponDO.getId()==0){
            return  Result.ofParamsError("更新失败,券码参数异常");
        }else {
            int  falg=couponService.update(couponDO);
            if(falg>0){
                return  Result.ofSuccess("券码更新成功");
            }
        }

        return  Result.ofError("券码更新失败");
    }
}
