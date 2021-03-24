package com.item.javaeeapi.config;

import feign.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ServiceFeignConfig
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-08 13:17
 * @Version: 1.0
 **/
@Configuration
public class ServiceFeignConfig {

    @Value("${service.feign.connectionTimeout}")
    private int connectTimeout ;

    @Value("${service.feign.readTimeout}")
    private int readTimeout ;

    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeout, readTimeout) ;
    }
}
