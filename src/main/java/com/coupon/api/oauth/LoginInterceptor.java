package com.coupon.api.oauth;

import com.coupon.api.entity.AccountDO;
import com.coupon.api.entity.ManageDO;
import com.coupon.api.entity.OauthTokenDO;
import com.coupon.api.service.AccountService;
import com.coupon.api.service.ManageService;
import com.coupon.api.service.OauthTokenService;
import com.coupon.api.utils.DateUtil;
import com.coupon.api.utils.HttpUtils;
import com.coupon.api.utils.Result;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
    @Autowired
    ManageService manageService;
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
        System.out.println(oauthTokenService);
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (token == null || token.equals("")) {
            token=request.getParameter("token");
            if (token == null || token.equals("")){
                printJson(request,response,"无效token信息,请重新登陆");
                return false;
            }
        }

        OauthTokenDO oauthTokenDO =oauthTokenService.queryByToken(token, DateUtil.getNowTime());
        if (oauthTokenDO == null) {
            printJson(request,response,"token过期,请重新登陆");
            return false;
        }

        String user=oauthTokenDO.getAccount();

        if("admin".equals(user)){
            ManageDO manageDO = new ManageDO();
            ManageDO manage=manageService.query(manageDO);
            if (manage == null||manage.getEnable()!=1) {
                printJson(request,response,"管理员失效,请联系管理员");
                return false;
            }
        }else {
            AccountDO accountDO = new AccountDO();
            accountDO.setAccount(user);
            AccountDO account =accountService.query(accountDO);
            if (account == null||account.getEnable()!=1) {
                printJson(request,response,"用户失效,请联系管理员");
                return false;
            }
        }



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpUtils.setHead(request,response);
    }

    private static void printJson(HttpServletRequest request,HttpServletResponse response ,String message) {
        Gson gson = new Gson();
        String content=gson.toJson(Result.ofTokenError(message));
        printContent(request,response, content);
    }

    private static void printContent(HttpServletRequest request,HttpServletResponse response, String content) {
        try {
            response.reset();
            HttpUtils.setHead(request,response);
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
