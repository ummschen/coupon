package com.coupon.api.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by arnold.zhu on 6/13/2017.
 */
@Configuration
@Data
public class DruidConfig {

    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    @Value("${spring.datasource.url}")
    private  String dbUrl;

    @Value("${spring.datasource.username}")
    private  String username;

    @Value("${spring.datasource.password}")
    private  String password;

    @Value("${spring.datasource.driverClassName}")
    private  String driverClassName;

/*    @Value("${initialSize}")
    private String initialSize;

    @Value("${minIdle}")
    private String minIdle;

    @Value("${maxActive}")
    private String maxActive;

    @Value("${maxWait}")
    private String maxWait;

    @Value("${timeBetweenEvictionRunsMillis}")
    private String timeBetweenEvictionRunsMillis;

    @Value("${minEvictableIdleTimeMillis}")
    private String minEvictableIdleTimeMillis;
*/
    @Value("${spring.datasource.validationQuery}")
    private  String validationQuery;



    @Value("${spring.datasource.logSlowSql}")
    private  String logSlowSql;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", username);
        reg.addInitParameter("loginPassword", password);
        reg.addInitParameter("logSlowSql", logSlowSql);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public  DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        System.out.println(dbUrl);
        System.out.println(username);
        System.out.println(password);
        System.out.println(driverClassName);
        System.out.println(validationQuery);
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        datasource.setInitialSize(10);
        datasource.setMinIdle(5);
        datasource.setMaxActive(20);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(300000);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);

        return datasource;
    }

}