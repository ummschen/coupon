package com.coupon.api.controller;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.entity.CouponStatDO;
import com.coupon.api.enums.CouponStatusEnum;
import com.coupon.api.service.CouponService;
import com.coupon.api.service.CouponStatService;
import com.coupon.api.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon/coupon")
@Api(tags={"券码管理"})
public class CouponController {
    @Autowired
    CouponService couponService;
    @Autowired
    CouponStatService couponStatService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "券码列表", httpMethod ="POST")
    public Result list(@RequestBody CouponDO couponDO){
        PagingModel<CouponDTO> CouponDOPagingModel = new PagingModel<>();
        List<CouponDTO> couponDTOList=couponService.queryList(couponDO);
        for (CouponDTO couponDTO : couponDTOList) {
            couponDTO.setStatusDesc(CouponStatusEnum.getCouponStatusEnum(couponDTO.getStatus()).getName());
        }
        int total =couponService.queryCount(couponDO);
        CouponDOPagingModel.setResults(couponDTOList);
        CouponDOPagingModel.setTotal(total);
        CouponDOPagingModel.setPageIndex(couponDO.getPageIndex());
        CouponDOPagingModel.setPageSize(couponDO.getPageSize());
        return  Result.ofSuccess(CouponDOPagingModel);
    }



    @RequestMapping(value = "/coupon_stat", method = {RequestMethod.POST})
    @ApiOperation(value = "券码商户统计列表", httpMethod ="POST")
    public Result coupon_stat(@RequestBody CouponStatDO couponStatDO){
        PagingModel<CouponStatDO> CouponDOPagingModel = new PagingModel<>();
        List<CouponStatDO> couponList=couponStatService.queryList(couponStatDO);
        int total =couponStatService.queryCount(couponStatDO);
        CouponDOPagingModel.setResults(couponList);
        CouponDOPagingModel.setTotal(total);
        CouponDOPagingModel.setPageIndex(couponStatDO.getPageIndex());
        CouponDOPagingModel.setPageSize(couponStatDO.getPageSize());
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
        if (couponDTO!=null&&StringUtils.isNotBlank(couponDTO.getEndTime())){
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
            return couponService.distribute(couponDTO);
        }
        return  Result.ofError("参数错误,券码分发失败");
    }

    @RequestMapping(value = "/writeoff", method = {RequestMethod.POST})
    @ApiOperation(value = "券码核销", httpMethod ="POST")
    public Result writeOff(@RequestBody CouponDO couponDO){
        if (couponDO!=null){
            return couponService.writeOff(couponDO);
        }
        return  Result.ofError("参数错误,券码核销失败");
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ApiOperation(value="券码导出",httpMethod ="GET")
    public void export(@RequestParam(required = false) CouponDO couponDO , HttpServletResponse response, HttpServletRequest request){

        List<CouponDTO> couponList=couponService.queryList(couponDO);
        ArrayList<Object[]> dataList = new ArrayList<>();

        for (CouponDTO coupon : couponList) {
            Integer id = coupon.getId();
            String couponCode=coupon.getCoupon();
            String type= coupon.getCouponType();
            double price = coupon.getPrice();
            String channel= coupon.getChannelCode();
            String business= coupon.getBusinessCode();
            Integer status =coupon.getStatus();

            String writeOffAccount=coupon.getWriteOffAccount();
            String endTime= coupon.getEndTime();
            String createTime= DateUtil.getNowTime(coupon.getCreateTime());
            String updateTime= DateUtil.getNowTime(coupon.getUpdateTime());
            Object[]  obj = {id==null?0:id,(StringUtils.isBlank(couponCode)?"无":couponCode),(StringUtils.isBlank(type)?"无":type),price==0?0:price,(StringUtils.isBlank(channel)?"无":channel)
                    ,(StringUtils.isBlank(business)?"无":business),CouponStatusEnum.getCouponStatusEnum(status).getName(),
                    (StringUtils.isBlank(writeOffAccount)?"无":writeOffAccount),(StringUtils.isBlank(endTime)?"无":endTime),(StringUtils.isBlank(createTime)?"无":createTime),(StringUtils.isBlank(updateTime)?"无":updateTime)};
            dataList.add(obj);
        }
        String[] arr = {"ID","券码","券码种类","金额","渠道","商户","状态","到期时间","核销账户","创建时间","更新时间"};
        ExportExcel exportExcel = new ExportExcel("Coupon", arr, dataList, response,request);
        try {
            exportExcel.export();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
