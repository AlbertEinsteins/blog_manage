package com.item.javaee.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: EsClientConfig
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-16 14:00
 * @Version: 1.0
 **/
@Configuration
public class EsClientConfig {

    private String hostname = "94.191.97.133" ;
    private Integer port = 9200 ;
    private String schema = "http" ;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostname, port, schema))
        ) ;
    }
}
