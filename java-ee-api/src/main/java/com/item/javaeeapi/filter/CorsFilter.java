package com.item.javaeeapi.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: CorsFilter
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-08 15:39
 * @Version: 1.0
 **/
@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse ;
        response.setHeader("Access-Control-Allow-Origin", "*") ;
        response.setHeader("Access-Control-Allow-Methods", "*") ;
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*") ;
        filterChain.doFilter(servletRequest, servletResponse) ;
    }
}
