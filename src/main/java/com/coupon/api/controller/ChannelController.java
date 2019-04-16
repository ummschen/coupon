package com.coupon.api.controller;

import com.coupon.api.dto.ChannelDTO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.service.ChannelService;
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

@RestController
@RequestMapping("/coupon/channel")
@Api(tags={"渠道管理"})
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    public Result list(@RequestBody ChannelDO channelDO){
        PagingModel<ChannelDTO> channelDOPagingModel = new PagingModel<>();
        List<ChannelDO> ChannelList=channelService.queryList(channelDO);
        List<ChannelDTO> channelDTOList= new ArrayList<>();
        for (ChannelDO channel : ChannelList) {
            channelDTOList.add(CopyUtil.copy(channel, ChannelDTO.class));
        }
        int total =channelService.queryCount(channelDO);
        channelDOPagingModel.setResults(channelDTOList);
        channelDOPagingModel.setTotal(total);
        channelDOPagingModel.setPageIndex(channelDO.getPageIndex());
        channelDOPagingModel.setPageSize(channelDO.getPageSize());
        return  Result.ofSuccess(channelDOPagingModel);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public Result save(@RequestBody ChannelDO channelDO){
        int  falg=channelService.save(channelDO);
        if(falg>0){
            return  Result.ofSuccess("渠道保存成功");
        }
        return  Result.ofError("渠道保存失败");
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody ChannelDO channelDO){
        if (channelDO==null||channelDO.getId()==0){
            return  Result.ofParamsError("更新失败,渠道参数异常");
        }else {
            int  falg=channelService.update(channelDO);
            if(falg>0){
                return  Result.ofSuccess("渠道更新成功");
            }
        }

        return  Result.ofError("渠道更新失败");
    }
}
