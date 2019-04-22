package com.coupon.api.oauth;

import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.OauthTokenDO;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.OauthTokenService;
import com.coupon.api.utils.DateUtil;
import com.coupon.api.utils.Result;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * token拦截器 * *
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    OauthTokenService oauthTokenService;
    @Autowired
    AccountService accountService;
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (token == null || token.equals("")) {
            printJson(response,"无效token信息,请重新登陆");
            return false;
        }

        OauthTokenDO oauthTokenDO =oauthTokenService.queryByToken(token, DateUtil.getNowTime());
        if (oauthTokenDO == null) {
            printJson(response,"token过期,请重新登陆");
            return false;
        }
        AccountDO accountDO = new AccountDO();
        accountDO.setAccount(oauthTokenDO.getAccount());
        AccountDO account =accountService.query(accountDO);
        if (account == null||account.getEnable()!=1) {
            printJson(response,"账户失效,请联系管理员");
            return false;
        }

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

    private static void printJson(HttpServletResponse response ,String message) {
        Gson gson = new Gson();
        String content=gson.toJson(Result.ofTokenError(message));
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

}
