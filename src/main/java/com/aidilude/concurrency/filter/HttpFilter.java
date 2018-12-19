package com.aidilude.concurrency.filter;

import com.aidilude.concurrency.example.threadLocal.InfoHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //访问地址
        String uri = request.getRequestURI();
        System.out.println("过虑地址：" + uri);
        String url = request.getServletPath();
        System.out.println("接口地址：" + url);
        Long threadID = Thread.currentThread().getId();
        log.info("do filter，{}，{}", threadID, url);
        //这里假设我想存储的info就是当前线程的ID
        InfoHolder.addInfo(threadID);
        //如果这个filter不想拦截这个请求，只是想做单纯的数据处理
        //最后一定要调用filterChain.doFilter(servletRequest, servletResponse)
        //这样表示：这个filter执行完，然后让请求继续被后续的filter处理
        //如果后续没有filter了，那么开始正式执行请求
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("destroy filter");
    }

}