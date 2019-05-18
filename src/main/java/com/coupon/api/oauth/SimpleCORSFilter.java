
package com.coupon.api.oauth;

import com.coupon.api.utils.HttpUtils;
import com.coupon.api.utils.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述:  允许跨域.
 *
 * @Author:
 * @Date: 2019/4/25 14:01
 **/
public class SimpleCORSFilter implements Filter {

    /**
     * 初始化
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // left blank intentionally
    }

    /**
     * 过滤
     *
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        setHeader(request, response);
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return;
            // servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpUtils.setHead(request,response);
    }

    /**
     * do nothing
     */
    @Override
    public void destroy() {
        // left blank intentionally
    }
}