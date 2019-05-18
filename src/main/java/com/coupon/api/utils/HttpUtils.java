package com.coupon.api.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtils {
    public static void setHead(HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        if (StringUtils.isBlank(origin) ||"null".equalsIgnoreCase("null"))
        {
            origin = "*";
        }
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,x-requested-with,accept,Content-Type,Authorization,Api-Version,Cache-Control,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "token");
    }
}
