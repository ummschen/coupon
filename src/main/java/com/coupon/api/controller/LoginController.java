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


    @RequestMapping(value = "/manage/login", method = {RequestMethod.POST})
    @ApiOperation(value = "后台登录", httpMethod ="POST")
    public Result manageLogin(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password){
        ManageDO loginManage = new ManageDO();
        loginManage.setUserName(userName);
        loginManage.setPassword(password);
        ManageDO manageDO=manageService.query(loginManage);
        if (manageDO!=null&&manageDO.getEnable()==1){
            ManageDTO manageDTO= CopyUtil.copy(manageDO, ManageDTO.class);
            String token=oauthTokenService.addLoginToken(userName);
            manageDTO.setToken(token);
            return  Result.ofSuccess(manageDTO);
        }
        return  Result.ofError("账号或密码错误");
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ApiOperation(value = "店员登录", httpMethod ="POST")
    public Result login(@RequestParam(value="username") String userName, @RequestParam(value="password") String password){
        AccountDO loginAccount = new AccountDO();
        loginAccount.setAccount(userName);
        loginAccount.setPassword(password);
        AccountDO accountDO=accountService.query(loginAccount);
        if (accountDO!=null&&accountDO.getEnable()==1){
            AccountDTO accountDTO= CopyUtil.copy(accountDO, AccountDTO.class);
            String token=oauthTokenService.addLoginToken(userName);
            accountDTO.setToken(token);
            return  Result.ofSuccess(accountDTO);
        }
        return  Result.ofError("账号或密码错误");
    }
}
