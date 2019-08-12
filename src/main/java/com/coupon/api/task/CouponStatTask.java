package com.coupon.api.task;

import com.coupon.api.mapper.CouponWriteDayDOMapper;
import com.coupon.api.mapper.CouponWriteMonthDOMapper;
import com.coupon.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class CouponStatTask {
    @Autowired
    CouponWriteDayDOMapper couponWriteDayDOMapper;

    @Autowired
    CouponWriteMonthDOMapper couponWriteMonthDOMapper;


    @Async
    @Scheduled(cron = "0 */5 * * * ?")
    public void  doTask()  {
        System.out.println("**********************定时统计开始"+ DateUtils.format(new Date(),DateUtils.FORMAT_SHORT)+"**********************");
        String  startTime = DateUtils.format(new Date(),DateUtils.FORMAT_SHORT)+" 00:00:00";
        String  endTime   = DateUtils.format(new Date(),DateUtils.FORMAT_SHORT)+" 23:59:59";
        couponWriteDayDOMapper.task(startTime,endTime);
        String  startDay = DateUtils.monthFristDay(new Date(),0)+" 00:00:00";
        String  endDay   = DateUtils.monthLastDay(new Date(),0)+" 23:59:59";
        couponWriteMonthDOMapper.task(startDay,endDay);
        System.out.println(startTime+"/n"+endTime+"/n"+startDay+"/n"+endDay+"/n");
        System.out.println("**********************定时统计结束"+ DateUtils.format(new Date())+"**********************");
    }


    @Async
    @Scheduled(cron = "0 10 0 * * ? ")
    public void lastDayTask() {
        System.out.println("**********************定时统计昨日开始"+ DateUtils.format(new Date())+"**********************");
        String  startTime = DateUtils.format(DateUtils.addDay(new Date(),-1),DateUtils.FORMAT_SHORT)+" 00:00:00";
        String  endTime   = DateUtils.format(DateUtils.addDay(new Date(),-1),DateUtils.FORMAT_SHORT)+" 23:59:59";
        couponWriteDayDOMapper.task(startTime,endTime);
        System.out.println(startTime+"/n"+endTime+"/n");
        System.out.println("**********************定时统计昨日结束"+ DateUtils.format(new Date())+"**********************");
    }


    @Async
    @Scheduled(cron = "0 15 0 1 * ? ")//0 15 0 1 * ? *
    public void lastMonthTask() {
        System.out.println("**********************定时统计上个月开始"+ DateUtils.format(new Date())+"**********************");
        String  startDay = DateUtils.monthFristDay(new Date(),-1)+" 00:00:00";
        String  endDay   = DateUtils.monthLastDay(new Date(),-1)+" 23:59:59";
        couponWriteMonthDOMapper.task(startDay,endDay);
        System.out.println(startDay+"/n"+endDay+"/n");
        System.out.println("**********************定时统计上个月结束"+ DateUtils.format(new Date())+"**********************");
    }

}
