package com.coupon.api;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(scanBasePackages = "com.coupon.api" )
@MapperScan(basePackages = {"com.coupon.api.mapper"})
@EnableScheduling
@Slf4j
public class CouponApplication  implements CommandLineRunner {
    public static void main(String[] args) {
        System.out.println("服务正在启动中。。。");
        SpringApplication.run(CouponApplication.class, args);
        System.out.println("启动完成！");
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("CouponApplication start OK");
    }

}
