/*
package com.coupon.api.oauth;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

*/
/**
 * token拦截器 * * @author sqc * @date 2018/8/2
 *//*

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (token == null || token.equals("")) {
            printJson(response, "");
            return false;
        }
        User user = (User) getRedisService().get(REDIS_USER_SESSION_KEY + ":" + token);
        if (user == null) {
            printJson(response, "");
            return false;
        }
        String userId = user.getId();
        User sqlUser = getUserService().getUserById(userId);
        String enterpriseId = getUserEnterpriseService().selectEnterpriseByUser(userId);
        if (enterpriseId == null) {
            getRedisService().set(REDIS_USER_SESSION_KEY + ":" + token, sqlUser, SSO_SESSION_EXPIRE);
            return true;
        }
        Enterprise sqlEnterprise = getEnterpriseService().selectEnterpriseById(enterpriseId);
        getRedisService().set(REDIS_USER_SESSION_KEY + ":" + token, sqlUser, SSO_SESSION_EXPIRE);
        getRedisService().set(REDIS_ENTERPRISE_SESSION_KEY + ":" + token, sqlEnterprise, SSO_SESSION_EXPIRE);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        // vary: Origin
        // Vary: Access-Control-Request-Method
        // Vary: Access-Control-Request-Headers
        // Access-Control-Allow-Origin: *
    }

    private static void printJson(HttpServletResponse response, String code) {
        ResponseResult responseResult = new ResponseResult(10086, false, "token过期,请重新登陆", null);
        String content = JSON.toJSONString(responseResult);
        printContent(response, content);
    }

    private static void printContent(HttpServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private EnterpriseService getEnterpriseService() {
        return SpringContextHolder.getBean(EnterpriseService.class);
    }

    private RedisService getRedisService() {
        return SpringContextHolder.getBean(RedisService.class);
    }

    private UserEnterpriseService getUserEnterpriseService() {
        return SpringContextHolder.getBean(UserEnterpriseService.class);
    }

    private UserService getUserService() {
        return SpringContextHolder.getBean(UserService.class);
    }
}
*/
