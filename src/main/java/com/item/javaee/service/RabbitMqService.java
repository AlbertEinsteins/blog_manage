package com.item.javaee.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.javaee.domain.RabbitMqMailMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RabbitMqService
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-07 11:28
 * @Version: 1.0
 **/
@Service
public class RabbitMqService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqService.class) ;


    @Autowired
    RabbitTemplate rabbitTemplate ;

    @Autowired
    ObjectMapper objectMapper ;

    @Value("${mail.rabbitmq.exchange}")
    private String exchange ;
    @Value("${mail.rabbitmq.rootingkey}")
    private String rootingKey ;
    /**
     * 发送消息到配置文件指定的exchange + rootingkey的消息队列
     *
     */
    public void convertAndSend(RabbitMqMailMsg data) {
        String jsonStr = null ;

        try {
            jsonStr = objectMapper.writeValueAsString(data) ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        //发送的消息实体
        this.rabbitTemplate.convertAndSend(exchange, rootingKey, jsonStr) ;
        LOGGER.info("消息发送者:" + data.getFrom() + ",消息接受者:" + data.getTo()) ;
    }
}
