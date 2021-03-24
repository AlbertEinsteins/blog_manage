package com.item.javaee.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @ClassName: JackSonConfig
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-07 21:12
 * @Version: 1.0
 **/
@Configuration
public class JackSonConfig {

    //向容器中注入一个ObjectMapper对象
    @Bean
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper mapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build() ;
    }
}
