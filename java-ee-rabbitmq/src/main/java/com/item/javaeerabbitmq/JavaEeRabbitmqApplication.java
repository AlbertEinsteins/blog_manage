package com.item.javaeerabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class JavaEeRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEeRabbitmqApplication.class, args);
    }

}
