package com.coupon.api.controller;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.CouponDTO;
import com.coupon.api.dto.CouponTypeDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.service.CouponTypeService;
import com.coupon.api.utils.CopyUtil;
import com.coupon.api.utils.PagingModel;
import com.coupon.api.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coupon/coupon_type")
@Api(tags={"券码类型管理"})
public class CouponTypeController {
    @Autowired
    CouponTypeService couponTypeService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "券码类型更新", httpMethod ="POST")
    public Result list(@RequestBody CouponTypeDO couponTypeDO){
        PagingModel<CouponTypeDTO> CouponTypeDOPagingModel = new PagingModel<>();
        List<CouponTypeDO> CouponTypeList=couponTypeService.queryList(couponTypeDO);
        List<CouponTypeDTO> couponTypeDTOList= new ArrayList<>();
        for (CouponTypeDO couponType : CouponTypeList) {
            couponTypeDTOList.add(CopyUtil.copy(couponType, CouponTypeDTO.class));
        }
        int total =couponTypeService.queryCount(couponTypeDO);
        CouponTypeDOPagingModel.setResults(couponTypeDTOList);
        CouponTypeDOPagingModel.setTotal(total);
        CouponTypeDOPagingModel.setPageIndex(couponTypeDO.getPageIndex());
        CouponTypeDOPagingModel.setPageSize(couponTypeDO.getPageSize());
        return  Result.ofSuccess(CouponTypeDOPagingModel);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiOperation(value = "券码类型更新", httpMethod ="POST")
    public Result save(@RequestBody CouponTypeDO couponTypeDO){
        int  falg=couponTypeService.save(couponTypeDO);
        if(falg>0){
            return  Result.ofSuccess("券码类型保存成功");
        }
        return  Result.ofError("券码类型保存失败");
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
        }

        return  Result.ofError("券码类型更新失败");
    }
}

