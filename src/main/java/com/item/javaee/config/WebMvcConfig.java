package com.item.javaee.config;

import com.item.javaee.utils.RedisClient;
import org.apache.commons.beanutils.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @ClassName: WebMvcConfig
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-03 12:50
 * @Version: 1.0
 **/
@Configuration
public class WebMvcConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            //permit cors
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                        .allowedOrigins("*")
                        .maxAge(3600)
                        .allowCredentials(true) ;
            }

        } ;
    }


    //为BeanUtils注册一个日期转化器类
    @Bean
    public Converter dateConverter() {
        return new Converter() {
            @SuppressWarnings("rawtype")
            public Object convert(Class type, Object value) {
                if (value == null) {
                    return null;
                }
                if (!(value instanceof String)) {
                    return value;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");
                try {
                    return sdf.parse((String) value);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        } ;
    }



    @Bean
    public RedisClient redisClient() {
        return new RedisClient() ;
    }

}
