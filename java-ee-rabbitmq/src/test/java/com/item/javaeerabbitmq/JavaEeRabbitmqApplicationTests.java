package com.item.javaeerabbitmq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.javaeerabbitmq.domain.QueryVo;
import com.item.javaeerabbitmq.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JavaEeRabbitmqApplicationTests {


    @Autowired
    RabbitTemplate rabbitTemplate ;

    @Test
    public void contextLoads() {

        rabbitTemplate.convertAndSend("exchange.direct", "javaee.registry", "asd") ;
    }

    @Test
    public void testJson() {
        String a = "{\"name\": 1, \"asd\": 2, \"age\": 3}" ;

        JsonNode aJson = JsonUtils.json2JsonNode(a) ;

        System.out.println(aJson) ;
    }

    @Test
    public void testMapMsg() {
        Map<String, Object> map = new HashMap<>() ;
        map.put("from", "xxx") ;
        map.put("to", "xxxx") ;
        map.put("htmlMsg", "qweasdzxcasdqweasdasd") ;



        this.rabbitTemplate.convertAndSend("exchange.direct", "javaee.registry", map) ;
    }


    @Autowired
    ObjectMapper mapper ;

    @Test
    public void testObjectMsg() throws JsonProcessingException {
        QueryVo vo = new QueryVo("aaa", "123") ;

        String x = mapper.writeValueAsString(vo) ;

        this.rabbitTemplate.convertAndSend("exchange.direct", "javaee.registry", x) ;
    }



    @Test
    public void testObjetMapper() throws JsonProcessingException {
//        String a = "{\"name\":123}" ;
        QueryVo v = new QueryVo("123", "222" ) ;

        String s = mapper.writeValueAsString(v) ;
        System.out.println(s) ;

        try {
            JsonNode jsonNode = mapper.readTree(s);

            System.out.println(jsonNode) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
