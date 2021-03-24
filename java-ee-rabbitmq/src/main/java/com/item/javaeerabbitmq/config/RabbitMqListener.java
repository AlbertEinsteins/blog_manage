package com.item.javaeerabbitmq.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.javaeerabbitmq.service.MailService;
import com.item.javaeerabbitmq.utils.JsonUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: RabbitMqListener
 * @Description 监听邮件消息队列，进行消费
 * @Author: jff
 * @Date: 2019-11-07 17:49
 * @Version: 1.0
 **/
@Component
public class RabbitMqListener {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MailService mailService;

    //消费邮件消息
    @RabbitListener(queues = "javaee.registry")
    public void consumeMailMsg(String data) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonNode != null) {
            String html = jsonNode.get("htmlMsg").asText();
            String from = jsonNode.get("from").asText();
            String to = jsonNode.get("to").asText();

            mailService.sendHtmlMsg(from, to, html);
        }
    }
}
