package com.item.javaeerabbitmq.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @ClassName: JacksonConfig
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-07 19:34
 * @Version: 1.0
 **/
@Configuration
public class JacksonConfig {

    @Bean
    @ConditionalOnMissingBean(ObjectMapper.class)   //如果容器中没有这个类的实例才进行注入
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.build() ;
    }
}
