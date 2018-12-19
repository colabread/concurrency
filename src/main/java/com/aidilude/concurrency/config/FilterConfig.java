package com.aidilude.concurrency.config;

import com.aidilude.concurrency.filter.HttpFilter;
import com.aidilude.concurrency.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public Filter testFilter(){
        return new TestFilter();
    }

    @Bean
    public Filter httpFilter(){
        return new HttpFilter();
    }

    @Bean
    public FilterRegistrationBean setTestFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(testFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean setHttpFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(httpFilter());
        filterRegistrationBean.addUrlPatterns("/threadLocal/*");
        filterRegistrationBean.setOrder(2);   //order的数值越小，在所有的filter中优先级越高
        return filterRegistrationBean;
    }

}