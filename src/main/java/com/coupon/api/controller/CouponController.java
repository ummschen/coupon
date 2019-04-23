package com.coupon.api.controller;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.service.CouponService;
import com.coupon.api.utils.CopyUtil;
import com.coupon.api.utils.DateUtil;
import com.coupon.api.utils.PagingModel;
import com.coupon.api.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon/coupon")
@Api(tags={"券码管理"})
public class CouponController {
    @Autowired
    CouponService couponService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "券码列表", httpMethod ="POST")
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
    @ApiOperation(value = "券码新增", httpMethod ="POST")
    public Result save(@RequestBody CouponDO couponDO){
        int  falg=couponService.save(couponDO);
        if(falg>0){
            return  Result.ofSuccess("券码保存成功");
        }
        return  Result.ofError("券码保存失败");
    }


    @RequestMapping(value = "/batch_generate", method = {RequestMethod.POST})
    @ApiOperation(value = "券码生成", httpMethod ="POST")
    public Result batchGenerate(@RequestBody CouponDTO couponDTO){
        if (couponDTO!=null){
            String endTime = couponDTO.getEndTime();
            try {
               long diff= DateUtil.getMilliDifference(endTime);
               if(diff>=0){
                   return  Result.ofError("到期时间必须大于当前时间");
                }
            }catch (ParseException exception){
                exception.printStackTrace();
            }
            int  falg=couponService.generate(couponDTO);
            if(falg>0){
                return  Result.ofSuccess("券码生成成功");
            }
        }
        return  Result.ofError("参数错误,券码生成失败");
    }
    @RequestMapping(value = "/batch_distribute", method = {RequestMethod.POST})
    @ApiOperation(value = "券码分发", httpMethod ="POST")
    public Result batchDistribute(@RequestBody CouponDTO couponDTO){
        if (couponDTO!=null){
            int  falg=couponService.generate(couponDTO);
            if(falg>0){
                return  Result.ofSuccess("券码生成成功");
            }
        }
        return  Result.ofError("参数错误,券码生成失败");
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ApiOperation(value = "券码更新", httpMethod ="POST")
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
