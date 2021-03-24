package com.item.javaee;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan(basePackages = "com.item.javaee.filter")
@EnableRabbit
@SpringBootApplication
@EnableEurekaClient
public class JavaEeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEeApplication.class, args);
    }

}
