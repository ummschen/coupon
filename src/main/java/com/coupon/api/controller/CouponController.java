package com.coupon.api.controller;

import com.coupon.api.dto.CouponDTO;
import com.coupon.api.entity.CouponDO;
import com.coupon.api.entity.CouponStatDO;
import com.coupon.api.entity.CouponWriteDayDO;
import com.coupon.api.entity.CouponWriteMonthDO;
import com.coupon.api.enums.CouponStatusEnum;
import com.coupon.api.service.CouponService;
import com.coupon.api.service.CouponStatService;
import com.coupon.api.service.CouponWriteDayService;
import com.coupon.api.service.CouponWriteMonthService;
import com.coupon.api.utils.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/coupon/coupon")
@Api(tags={"券码管理"})
public class CouponController {
    @Autowired
    CouponService couponService;
    @Autowired
    CouponStatService couponStatService;
    @Autowired
    CouponWriteDayService couponWriteDayService;
    @Autowired
    CouponWriteMonthService couponWriteMonthService;

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "券码列表", httpMethod ="POST")
    public Result list(@RequestBody CouponDO couponDO){
        PagingModel<CouponDTO> couponDTOPagingModel = new PagingModel<>();
        List<CouponDTO> couponDTOList=couponService.queryList(couponDO);
        for (CouponDTO couponDTO : couponDTOList) {
            couponDTO.setStatusDesc(CouponStatusEnum.getCouponStatusEnum(couponDTO.getStatus()).getName());
        }
        int total =couponService.queryCount(couponDO);
        couponDTOPagingModel.setResults(couponDTOList);
        couponDTOPagingModel.setTotal(total);
        couponDTOPagingModel.setPageIndex(couponDO.getPageIndex());
        couponDTOPagingModel.setPageSize(couponDO.getPageSize());
        return  Result.ofSuccess(couponDTOPagingModel);
    }


    @ResponseBody
    @RequestMapping(value = "/totalPrice", method = {RequestMethod.POST})
    @ApiOperation(value = "券码金额总数", httpMethod ="POST")
    public Result totalPrice(@RequestBody CouponDO couponDO){
        Map<String ,Double> map=new HashMap<>();
        double total =couponService.sumPrice(couponDO);
        map.put("sumPrice",total);
        return  Result.ofSuccess(map);
    }

    @ResponseBody
    @RequestMapping(value = "/coupon_stat", method = {RequestMethod.POST})
    @ApiOperation(value = "券码商户统计列表", httpMethod ="POST")
    public Result couponStat(@RequestBody CouponStatDO couponStatDO){
        PagingModel<CouponStatDO> CouponDOPagingModel = new PagingModel<>();
        List<CouponStatDO> couponList=couponStatService.queryList(couponStatDO);
        int total =couponStatService.queryCount(couponStatDO);
        CouponDOPagingModel.setResults(couponList);
        CouponDOPagingModel.setTotal(total);
        CouponDOPagingModel.setPageIndex(couponStatDO.getPageIndex());
        CouponDOPagingModel.setPageSize(couponStatDO.getPageSize());
        return  Result.ofSuccess(CouponDOPagingModel);
    }



    @ResponseBody
    @RequestMapping(value = "/coupon_write_day", method = {RequestMethod.POST})
    @ApiOperation(value = "券码核销每日统计", httpMethod ="POST")
    public Result couponWriteDay(@RequestBody CouponWriteDayDO couponWriteDayDO){
        PagingModel<CouponWriteDayDO> CouponWriteDayPagingModel = new PagingModel<>();
        List<CouponWriteDayDO> couponList=couponWriteDayService.queryList(couponWriteDayDO);
        int total =couponWriteDayService.queryCount(couponWriteDayDO);
        CouponWriteDayPagingModel.setResults(couponList);
        CouponWriteDayPagingModel.setTotal(total);
        CouponWriteDayPagingModel.setPageIndex(couponWriteDayDO.getPageIndex());
        CouponWriteDayPagingModel.setPageSize(couponWriteDayDO.getPageSize());
        return  Result.ofSuccess(CouponWriteDayPagingModel);
    }


    @ResponseBody
    @RequestMapping(value = "/coupon_write_month", method = {RequestMethod.POST})
    @ApiOperation(value = "券码核销每月统计", httpMethod ="POST")
    public Result couponWriteMonth(@RequestBody CouponWriteMonthDO couponWriteMonthDO){
        PagingModel<CouponWriteMonthDO> couponWriteMonthDOPagingModel = new PagingModel<>();
        List<CouponWriteMonthDO> couponList=couponWriteMonthService.queryList(couponWriteMonthDO);
        int total =couponWriteMonthService.queryCount(couponWriteMonthDO);
        couponWriteMonthDOPagingModel.setResults(couponList);
        couponWriteMonthDOPagingModel.setTotal(total);
        couponWriteMonthDOPagingModel.setPageIndex(couponWriteMonthDO.getPageIndex());
        couponWriteMonthDOPagingModel.setPageSize(couponWriteMonthDO.getPageSize());
        return  Result.ofSuccess(couponWriteMonthDOPagingModel);
    }








    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiOperation(value = "券码新增", httpMethod ="POST")
    public Result save(@RequestBody CouponDO couponDO){
        int  falg=couponService.save(couponDO);
        if(falg>0){
            return  Result.ofSuccess("券码保存成功");
        }
        return  Result.ofError("券码保存失败");
    }

    @ResponseBody
    @RequestMapping(value = "/batch_generate", method = {RequestMethod.POST})
    @ApiOperation(value = "券码生成", httpMethod ="POST")
    public Result batchGenerate(@RequestBody CouponDTO couponDTO){
        if (couponDTO!=null&&couponDTO.getEndTime()!=null){
            String endTime = DateUtil.getNowTime(couponDTO.getEndTime());
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
    @ResponseBody
    @RequestMapping(value = "/batch_distribute", method = {RequestMethod.POST})
    @ApiOperation(value = "券码分发", httpMethod ="POST")
    public Result batchDistribute(@RequestBody CouponDTO couponDTO){
        if (couponDTO!=null){
            return couponService.distribute(couponDTO);
        }
        return  Result.ofError("参数错误,券码分发失败");
    }
    @ResponseBody
    @RequestMapping(value = "/writeoff", method = {RequestMethod.POST})
    @ApiOperation(value = "券码核销", httpMethod ="POST")
    public Result writeOff(@RequestBody CouponDO couponDO){
        if (couponDO!=null){
            return couponService.writeOff(couponDO);
        }
        return  Result.ofError("参数错误,券码核销失败");
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ApiOperation(value="券码导出",httpMethod ="GET")//=sacs200&=sacs&=
    public void export(@RequestParam(required = false) String couponType ,@RequestParam(required = false) String businessCode ,
                       @RequestParam(required = false) String channelCode, @RequestParam(required = false) String status,
                       @RequestParam(required = false) String startWriteOffTime ,@RequestParam(required = false) String endWriteOffTime ,
                       @RequestParam(required = false) String startDistributeTime ,@RequestParam(required = false) String endDistributeTime ,@RequestParam(required = false)String distributeSeq,
                       HttpServletResponse response, HttpServletRequest request){
        CouponDO couponDO= new CouponDO();
        couponDO.setCouponType(couponType);
        couponDO.setBusinessCode(businessCode);
        couponDO.setChannelCode(channelCode);
        if(status!=null&&!status.equals("")){
            couponDO.setStatus(Integer.parseInt(status));
        }
        couponDO.setStartWriteOffTime(startWriteOffTime);
        couponDO.setEndWriteOffTime(endWriteOffTime);
        couponDO.setStartDistributeTime(startDistributeTime);
        couponDO.setEndDistributeTime(endDistributeTime);
        couponDO.setDistributeSeq(distributeSeq);
        couponDO.setPageIndex(null);
        couponDO.setPageSize(null);
        List<CouponDTO> couponList=couponService.queryList(couponDO);
        List<Map<String ,Object>> groupByType=couponService.groupByType(couponDO);

        ArrayList<Object[]> dataList = new ArrayList<>();

        for (CouponDTO coupon : couponList) {
            Integer id = coupon.getId();
            String couponCode=coupon.getCoupon();
            String type= coupon.getCouponTypeName();
            String seq=coupon.getDistributeSeq();
            double price = coupon.getPrice();
            String channel= coupon.getChannelName();
            String business= coupon.getBusinessName();
            String writeOffAccountName= coupon.getWriteOffAccountName();
            String distributeTime= DateUtil.getNowTime(coupon.getDistributeTime());
            Integer status1 =coupon.getStatus();

            String writeOffAccount=coupon.getWriteOffAccount();
            String endTime=DateUtil.getNowTime( coupon.getEndTime());
            String writeOffTime= DateUtil.getNowTime(coupon.getWriteOffTime());
            String createTime= DateUtil.getNowTime(coupon.getCreateTime());
            String updateTime= DateUtil.getNowTime(coupon.getUpdateTime());
            Object[]  obj = {id==null?0:id,(StringUtils.isBlank(seq)?"无":seq),(StringUtils.isBlank(couponCode)?"无":couponCode),(StringUtils.isBlank(type)?"无":type),price==0?0:price,(StringUtils.isBlank(channel)?"无":channel)
                    ,(StringUtils.isBlank(business)?"无":business),(StringUtils.isBlank(writeOffAccountName)?"无":writeOffAccountName),CouponStatusEnum.getCouponStatusEnum(status1).getName(),
                    (StringUtils.isBlank(writeOffAccount)?"无":writeOffAccount),(StringUtils.isBlank(distributeTime)?"无":distributeTime),(StringUtils.isBlank(writeOffTime)?"无":writeOffTime),(StringUtils.isBlank(endTime)?"无":endTime),(StringUtils.isBlank(createTime)?"无":createTime),(StringUtils.isBlank(updateTime)?"无":updateTime)};
            dataList.add(obj);
        }
        int sum=0;
        Object[]  tyepCount ={"分发类型","分发数量"," "," "," "," "," "," "," "," "," "," "," "," "," "};
        dataList.add(tyepCount);
        for (Map<String, Object> map : groupByType) {
            String Typename=(String) map.get("name");
            long count =(long)map.get("con");
            Object[]  obj ={Typename,count," "," "," "," "," "," "," "," "," "," "," "," "," "};
            dataList.add(obj);
            sum+=count;
        }
        Object[]  couponSum ={"总量",sum," "," "," "," "," "," "," "," "," "," "," "," "," "};
        dataList.add(couponSum);
        String[] arr = {"ID","分发批次","券码","券码种类","金额","渠道","商户","门店名称","状态","核销账户","分发时间","核销时间","到期时间","创建时间","更新时间"};
        ExportExcel exportExcel = new ExportExcel("券码", arr, dataList, response,request);
        try {
            exportExcel.export();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @RequestMapping(value = "/coupon_stat_export", method = {RequestMethod.GET})
    @ApiOperation(value = "券码商户统计导出", httpMethod ="POST")
    public void coupon_stat_export(@RequestParam(required = false) String couponType , @RequestParam(required = false) String businessCode ,
                                   @RequestParam(required = false) String channelCode, @RequestParam(required = false) BigDecimal typePrice, HttpServletResponse response, HttpServletRequest request){
        CouponStatDO couponStatDO = new CouponStatDO();
        couponStatDO.setCouponType(couponType);
        couponStatDO.setBusinessCode(businessCode);
        couponStatDO.setChannelCode(channelCode);
        couponStatDO.setTypePrice(typePrice);
        couponStatDO.setPageIndex(null);
        couponStatDO.setPageSize(null);
        List<CouponStatDO> couponStatList=couponStatService.queryList(couponStatDO);
        ArrayList<Object[]> dataList = new ArrayList<>();

        for (CouponStatDO couponStat : couponStatList) {
            Integer id = couponStat.getId();
            String businessName =couponStat.getBusinessName();
            String couponTypeName  =couponStat.getCouponTypeName();

            String channelName =couponStat.getChannelName();
            Integer writeOff =couponStat.getWriteOff();
            Integer unWriteOff =couponStat.getUnWriteOff();

            Integer couponTotal =couponStat.getCouponTotal();
            String createTime= DateUtil.getNowTime(couponStat.getCreateTime());
            String updateTime= DateUtil.getNowTime(couponStat.getUpdateTime());

            Object[]  obj = {id==null?0:id,(StringUtils.isBlank(businessName)?"无":businessName),(StringUtils.isBlank(couponTypeName)?"无":couponTypeName),
                    (StringUtils.isBlank(channelName)?"无":channelName),writeOff==null?0:writeOff,
                    writeOff==null?0:writeOff ,unWriteOff==null?0:unWriteOff ,couponTotal==null?0:couponTotal ,
                    (StringUtils.isBlank(createTime)?"无":createTime),(StringUtils.isBlank(updateTime)?"无":updateTime)};
            dataList.add(obj);
        }
        String[] arr = {"ID","商户","券码种类","渠道","价格","核销数","未核销数","总数","创建时间","更新时间"};
        ExportExcel exportExcel = new ExportExcel("券码统计", arr, dataList, response,request);
        try {
            exportExcel.export();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
