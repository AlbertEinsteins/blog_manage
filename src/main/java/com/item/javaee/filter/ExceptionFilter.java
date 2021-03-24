package com.item.javaee.filter;

import com.item.javaee.utils.JacksonUtils;
import com.item.javaee.utils.JsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @ClassName: ExceptionFilter
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 21:56
 * @Version: 1.0
 **/
@WebFilter(urlPatterns = "/*")
public class ExceptionFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionFilter.class) ;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        LOG.info("XXXXXXX") ;
        try {
            filterChain.doFilter(servletRequest, servletResponse) ;
        } catch (Exception ex) {
            ex.printStackTrace();
            Object rs = JsonBuilder.buildJson(null, 500, "错误!") ;
            servletResponse.getWriter().println(JacksonUtils.object2Str(rs)) ;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void destroy() {

    }
}
