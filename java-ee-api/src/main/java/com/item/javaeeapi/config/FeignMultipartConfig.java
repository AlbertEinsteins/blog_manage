package com.item.javaeeapi.config;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: FeignMultipartConfig
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-07 18:05
 * @Version: 1.0
 **/
@Configuration
public class FeignMultipartConfig {
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(() -> {
            return new HttpMessageConverters(new RestTemplate().getMessageConverters()) ;
        })) ;
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return Logger.Level.FULL ;
    }

}
