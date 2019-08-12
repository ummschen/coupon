package com.coupon.api.controller;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.service.AccountService;
import com.coupon.api.utils.PagingModel;
import com.coupon.api.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupon/account")
@Api(tags={"店员管理"})
public class AccountController {
    @Autowired
    AccountService accountService;


    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "账户详细列表", httpMethod ="POST")
    public Result list(@RequestBody AccountDO accountDO){
        PagingModel<AccountDTO> accountDTOPagingModel = new PagingModel<>();
        List accountList=accountService.queryList(accountDO);
        int total =accountService.queryCount(accountDO);
        accountDTOPagingModel.setResults(accountList);
        accountDTOPagingModel.setTotal(total);
        accountDTOPagingModel.setPageIndex(accountDO.getPageIndex());
        accountDTOPagingModel.setPageSize(accountDO.getPageSize());
        return  Result.ofSuccess(accountDTOPagingModel);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiOperation(value = "账户新增", httpMethod ="POST")
    public Result save(@RequestBody AccountDO accountDO){
        int  falg=accountService.save(accountDO);
        if( falg== -2){
            return  Result.ofError("保存失败！！！该用户已存在");
        }
        if(falg>0){
            return  Result.ofSuccess("用户保存成功");
        }
        return  Result.ofError("用户保存失败");
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ApiOperation(value = "账户更新", httpMethod ="POST")
    public Result update(@RequestBody AccountDO accountDO){
        if (accountDO==null||accountDO.getId()==0){
            return  Result.ofParamsError("更新失败,用户参数异常");
        }else {
            int  falg=accountService.update(accountDO);
            if(falg>0){
                return  Result.ofSuccess("用户更新成功");
            }
        }

        return  Result.ofError("用户更新失败");
    }
}
