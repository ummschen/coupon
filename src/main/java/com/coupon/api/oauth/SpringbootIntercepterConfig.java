//package com.coupon.api.oauth;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class SpringbootIntercepterConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/manage/login",
//                        "/swagger-resources/**","**/**",
//                        "/manage/login",
//                        "/login",
//                        "/user/makesure/**",
//                        "/user/has/**",
//                        "/attachment/**");
//        super.addInterceptors(registry);
//    }
//}
