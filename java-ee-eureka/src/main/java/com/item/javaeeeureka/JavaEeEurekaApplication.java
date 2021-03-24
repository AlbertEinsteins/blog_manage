package com.item.javaeeeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class JavaEeEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEeEurekaApplication.class, args);
    }

}
