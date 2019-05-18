package com.coupon.api.controller;

import com.coupon.api.dto.OauthTokenDTO;
import com.coupon.api.entity.OauthTokenDO;
import com.coupon.api.service.OauthTokenService;
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

/*@RestController
@RequestMapping("/coupon/token")
@Api(tags={"用户token"})*/
public class OauthTokenController {
    @Autowired
    OauthTokenService oauthTokenService;

   /* @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public Result list(@RequestBody OauthTokenDO oauthTokenDO){
        PagingModel<OauthTokenDTO> accountDTOPagingModel = new PagingModel<>();
        List<OauthTokenDO> oauthTokenList=oauthTokenService.queryList(oauthTokenDO);
        List<OauthTokenDTO> couponTypeDTOList= new ArrayList<>();
        for (OauthTokenDO oauthToken : oauthTokenList) {
            couponTypeDTOList.add(CopyUtil.copy(oauthToken, OauthTokenDTO.class));
        }
        int total =oauthTokenService.queryCount(oauthTokenDO);
        accountDTOPagingModel.setResults(couponTypeDTOList);
        accountDTOPagingModel.setTotal(total);
        accountDTOPagingModel.setPageIndex(oauthTokenDO.getPageIndex());
        accountDTOPagingModel.setPageSize(oauthTokenDO.getPageSize());
        return  Result.ofSuccess(accountDTOPagingModel);
    }*/

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public Result save(@RequestBody OauthTokenDO oauthTokenDO){
        int  falg=oauthTokenService.save(oauthTokenDO);
        if(falg>0){
            return  Result.ofSuccess("用户token保存成功");
        }
        return  Result.ofError("用户token保存失败");
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody OauthTokenDO oauthTokenDO){
        if (oauthTokenDO==null||oauthTokenDO.getId()==0){
            return  Result.ofParamsError("更新失败,用户token参数异常");
        }else {
            int  falg=oauthTokenService.update(oauthTokenDO);
            if(falg>0){
                return  Result.ofSuccess("用户token更新成功");
            }
        }

        return  Result.ofError("用户token更新失败");
    }
}
