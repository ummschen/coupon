package com.coupon.api.controller;

import com.coupon.api.dto.AccountDTO;
import com.coupon.api.dto.ManageDTO;
import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.CouponTypeDO;
import com.coupon.api.entity.ManageDO;
import com.coupon.api.entity.OauthTokenDO;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.ManageService;
import com.coupon.api.service.OauthTokenService;
import com.coupon.api.utils.CopyUtil;
import com.coupon.api.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.Account;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping()
@Api(tags={"登录管理"})
public class LoginController {
    @Autowired
    ManageService manageService;
    @Autowired
    AccountService accountService;
    @Autowired
    OauthTokenService oauthTokenService;


    @RequestMapping(value = "/coupon/manage/login", method = {RequestMethod.POST})
    @ApiOperation(value = "后台登录", httpMethod ="POST")
    public Result manageLogin(@RequestBody ManageDO loginManage){
        if(loginManage==null||loginManage.getUserName()==null||loginManage.getPassword()==null){
            return  Result.ofError("账号或密码错误");
        }
        ManageDO loginManageDO = new ManageDO();
        loginManageDO.setUserName(loginManage.getUserName());
        loginManageDO.setPassword(loginManage.getPassword());
        ManageDO manageDO=manageService.query(loginManageDO);
        if (manageDO!=null&&manageDO.getEnable()==1){
            ManageDTO manageDTO= CopyUtil.copy(manageDO, ManageDTO.class);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 2);
            String token=oauthTokenService.addLoginToken(loginManage.getUserName(),date);
            manageDTO.setToken(token);
            return  Result.ofSuccess(manageDTO);
        }
        return  Result.ofError("账号或密码错误");
    }

    @RequestMapping(value = "/coupon/login", method = {RequestMethod.POST})
    @ApiOperation(value = "店员登录", httpMethod ="POST")
    public Result login(@RequestBody AccountDO account ){
        if(account==null||account.getAccount()==null||account.getPassword()==null){
            return  Result.ofError("账号或密码错误");
        }
        AccountDO loginAccount = new AccountDO();
        loginAccount.setAccount(account.getAccount());
        loginAccount.setPassword(account.getPassword());
        AccountDO accountDO=accountService.query(loginAccount);
        if (accountDO!=null&&accountDO.getEnable()==1){
            AccountDTO accountDTO= CopyUtil.copy(accountDO, AccountDTO.class);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 10);
            String token=oauthTokenService.addLoginToken(account.getAccount(),date);
            accountDTO.setToken(token);
            accountDTO.setTokenEndTime(date);
            return  Result.ofSuccess(accountDTO);
        }
        return  Result.ofError("账号或密码错误");
    }


    @RequestMapping(value = "/coupon/test", method = {RequestMethod.GET})
    @ApiOperation(value = "店员登录", httpMethod ="GET")
    public Result test( ){
        return  Result.ofSuccess("TEST");
    }
}
