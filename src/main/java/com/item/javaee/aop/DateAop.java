package com.item.javaee.aop;

import com.item.javaee.entity.Article;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: DateAop
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-23 20:15
 * @Version: 1.0
 **/
//@Component
//@Aspect
public class DateAop {
    public static final Logger LOG = LoggerFactory.getLogger(DateAop.class) ;

//    @Pointcut("execution(* com.item.javaee.controller.MainController.getPageableArticle(..))")
    public void pointCut() { }


    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfter(Object ret) {
        //这个结果是list<article>
        @SuppressWarnings("unchecked")
        List<Article> list = (List<Article>) (((HashMap)((HashMap) ret).get("data"))).get("data") ;


        //将UTC时间转化为+8时间
//        System.out.println(list) ;
        list.forEach(article -> {
            Calendar calendar = Calendar.getInstance() ;
            calendar.setTime(article.getPublishTime()) ;
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8) ;
            article.setPublishTime(calendar.getTime()) ;
//            System.out.println(article) ;
        });

    }
}
