package com.item.javaee.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FirstTest
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-06 23:33
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FirstTest {

    public void send() {
        
    }

    @Autowired
    RabbitTemplate rabbitTemplate ;
    @Test
    public void receive() {
        Message atguigu = this.rabbitTemplate.receive("atguigu");

        System.out.println(atguigu.getClass()) ;
        System.out.println(atguigu.getBody()) ;
        System.out.println(atguigu.getMessageProperties()) ;
    }

    @Test
    public void receive2() {
        Object atguigu = this.rabbitTemplate.receiveAndConvert("atguigu");

        System.out.println(atguigu) ;
    }


    @Test
    public void send_2() {
        System.out.println("发送消息") ;
        Map<String, Object> a = new HashMap<>() ;
        a.put("name", "张三") ;
        a.put("age", 12) ;


        rabbitTemplate.convertAndSend("exchange.direct", "atguigu", a);
    }

    @Test
    public void receive_2() {
        System.out.println("消费消息") ;

        Object atguigu = rabbitTemplate.receiveAndConvert("atguigu");

        if(atguigu != null) {
            System.out.println(atguigu.getClass());
            System.out.println(atguigu) ;
        }
    }
}
