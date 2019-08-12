package com.coupon.api.controller;

import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.CopyUtil;
import com.coupon.api.utils.PagingModel;
import com.coupon.api.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon/coupon_type")
@Api(tags={"券码类型管理"})
public class CouponTypeController {
    @Autowired
    CouponTypeService couponTypeService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "券码类型列表_分页", httpMethod ="POST")
    public Result list(@RequestBody CouponTypeDO couponTypeDO){
        PagingModel<CouponTypeDTO> CouponTypeDOPagingModel = new PagingModel<>();
        List<CouponTypeDTO> CouponTypeList=couponTypeService.queryList(couponTypeDO);
        int total =couponTypeService.queryCount(couponTypeDO);
        CouponTypeDOPagingModel.setResults(CouponTypeList);
        CouponTypeDOPagingModel.setTotal(total);
        CouponTypeDOPagingModel.setPageIndex(couponTypeDO.getPageIndex());
        CouponTypeDOPagingModel.setPageSize(couponTypeDO.getPageSize());
        return  Result.ofSuccess(CouponTypeDOPagingModel);
    }
    @RequestMapping(value = "/list_all", method = {RequestMethod.POST})
    @ApiOperation(value = "券码类型列表_所有", httpMethod ="POST")
    public Result listAll(@RequestBody CouponTypeDO couponTypeDO){
        if(couponTypeDO==null){
            couponTypeDO = new CouponTypeDO();
        }
        PagingModel<CouponTypeDTO> CouponTypeDOPagingModel = new PagingModel<>();
        couponTypeDO.setPageIndex(null);
        couponTypeDO.setPageSize(null);
        List<CouponTypeDTO> CouponTypeList=couponTypeService.queryList(couponTypeDO);
        CouponTypeDOPagingModel.setResults(CouponTypeList);
        int total =couponTypeService.queryCount(couponTypeDO);
        CouponTypeDOPagingModel.setTotal(total);
        CouponTypeDOPagingModel.setPageSize(total);
        return  Result.ofSuccess(CouponTypeDOPagingModel);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiOperation(value = "券码类型保存", httpMethod ="POST")
    public Result save(@RequestBody CouponTypeDO couponTypeDO){
        int  falg=couponTypeService.save(couponTypeDO);
        if( falg== -2){
            return   Result.ofError("保存失败！！！该券码类型编码已存在");
        }
        if(falg>0){
            return  Result.ofSuccess("券码类型保存成功");
        }
        return  Result.ofError("券码类型保存失败");
    }

    @RequestMapping(value = "/saves", method = {RequestMethod.POST})
    @ApiOperation(value = "批量券码类型保存", httpMethod ="POST")
    public Result saves(@RequestBody CouponTypeDO couponTypeDO){
        return  couponTypeService.saves(couponTypeDO);
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ApiOperation(value = "券码类型更新", httpMethod ="POST")
    public Result update(@RequestBody CouponTypeDO couponTypeDO){
        if (couponTypeDO==null||couponTypeDO.getId()==0){
            return  Result.ofParamsError("更新失败,券码类型参数异常");
        }else {
            int  falg=couponTypeService.update(couponTypeDO);
            if(falg>0){
                return  Result.ofSuccess("券码类型更新成功");
            }
            if( falg<0){
                return   Result.ofError("更新失败！！！"+couponTypeDO.getName()+" 已有相同券码类型名称存在");
            }
        }

        return  Result.ofError("券码类型更新失败");
    }
}

