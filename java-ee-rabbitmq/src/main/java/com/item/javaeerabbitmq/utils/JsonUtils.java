package com.item.javaeerabbitmq.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNode;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @ClassName: JsonUtils
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-07 19:19
 * @Version: 1.0
 **/
public final class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper() ;


    public static JsonNode json2JsonNode(String jsonStr) {
        JsonNode rs = null ;
        try {
            rs = objectMapper.readTree(jsonStr) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs ;
    }
}
