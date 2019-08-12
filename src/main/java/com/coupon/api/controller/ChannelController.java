package com.coupon.api.controller;

import com.coupon.api.dto.ChannelDTO;
import com.coupon.api.entity.ChannelDO;
import com.coupon.api.service.ChannelService;
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
@RequestMapping("/coupon/channel")
@Api(tags={"渠道管理"})
public class ChannelController {
    @Autowired
    ChannelService channelService;

    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @ApiOperation(value = "渠道列表_分页", httpMethod ="POST")
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

    @RequestMapping(value = "/list_all", method = {RequestMethod.POST})
    @ApiOperation(value = "渠道列表_所有", httpMethod ="POST")
    public Result listAll(@RequestBody ChannelDO channelDO){

        if(channelDO==null){
             channelDO = new ChannelDO();
        }
        PagingModel<ChannelDTO> channelDOPagingModel = new PagingModel<>();
        channelDO.setPageIndex(null);
        channelDO.setPageSize(null);
        List<ChannelDO> ChannelList=channelService.queryList(channelDO);
        List<ChannelDTO> channelDTOList= new ArrayList<>();
        for (ChannelDO channel : ChannelList) {
            channelDTOList.add(CopyUtil.copy(channel, ChannelDTO.class));
        }
        int total =channelService.queryCount(channelDO);
        channelDOPagingModel.setResults(channelDTOList);
        channelDOPagingModel.setTotal(total);
        channelDOPagingModel.setPageSize(total);
        return  Result.ofSuccess(channelDOPagingModel);
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ApiOperation(value = "渠道新增", httpMethod ="POST")
    public Result save(@RequestBody ChannelDO channelDO){
        int  falg=channelService.save(channelDO);
        if( falg== -2){
            return  Result.ofError("保存失败！！！该渠道编码已存在");
        }
        if(falg>0){
            return  Result.ofSuccess("渠道保存成功");
        }
        return  Result.ofError("渠道保存失败");
    }


    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ApiOperation(value = "渠道更新", httpMethod ="POST")
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
