package com.coupon.api.controller;

import com.coupon.api.dto.BusinessDTO;
import com.coupon.api.entity.BusinessDO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.service.BusinessService;
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
@RequestMapping("/coupon/business")
@Api(tags={"商户管理"})
public class BusinessController {
    @Autowired
    BusinessService businessService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "商户列表_分页", httpMethod ="POST")
    public Result list(@RequestBody BusinessDO businessDO){
        PagingModel<BusinessDTO> businessDTOPagingModel = new PagingModel<>();
        List<BusinessDO> businessList=businessService.queryList(businessDO);
        List <BusinessDTO> businessDTOList= new ArrayList<>();
        for (BusinessDO business : businessList) {
            businessDTOList.add(CopyUtil.copy(business,BusinessDTO.class));
        }
        int total =businessService.queryCount(businessDO);
        businessDTOPagingModel.setResults(businessDTOList);
        businessDTOPagingModel.setTotal(total);
        businessDTOPagingModel.setPageIndex(businessDO.getPageIndex());
        businessDTOPagingModel.setPageSize(businessDO.getPageSize());
        return  Result.ofSuccess(businessDTOPagingModel);
    }

    @RequestMapping(value = "/list_all", method = {RequestMethod.POST})
    @ApiOperation(value = "商户列表_所有", httpMethod ="POST")
    public Result listAll(@RequestBody BusinessDO businessDO){
        if(businessDO==null){
            businessDO = new BusinessDO();
        }
        PagingModel<BusinessDTO> businessDTOPagingModel = new PagingModel<>();
        businessDO.setPageIndex(null);
        businessDO.setPageSize(null);
        List<BusinessDO> businessList=businessService.queryList(businessDO);
        List <BusinessDTO> businessDTOList= new ArrayList<>();
        for (BusinessDO business : businessList) {
            businessDTOList.add(CopyUtil.copy(business,BusinessDTO.class));
        }
        int total =businessService.queryCount(businessDO);
        businessDTOPagingModel.setTotal(total);
        businessDTOPagingModel.setPageSize(total);
        businessDTOPagingModel.setResults(businessDTOList);
        return  Result.ofSuccess(businessDTOPagingModel);
    }


    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiOperation(value = "商户保存", httpMethod ="POST")
    public Result save(@RequestBody BusinessDO businessDO){
        int  falg=businessService.save(businessDO);
        if( falg== -2){
            return  Result.ofError("保存失败！！！该商户编码已存在");
        }
        if(falg>0){
            return  Result.ofSuccess("商户保存成功");
        }
        return  Result.ofError("商户保存失败");
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ApiOperation(value = "商户更新", httpMethod ="POST")
    public Result update(@RequestBody BusinessDO businessDO){
        if (businessDO==null||businessDO.getId()==0){
            return  Result.ofParamsError("更新失败,商户参数异常");
        }else {
            int  falg=businessService.update(businessDO);
            if(falg>0){
                return  Result.ofSuccess("商户更新成功");
            }
        }

        return  Result.ofError("商户更新失败");
    }
}
