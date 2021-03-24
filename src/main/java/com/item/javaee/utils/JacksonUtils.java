package com.item.javaee.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: JacksonUtils
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 22:28
 * @Version: 1.0
 **/
public class JacksonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper() ;

    private JacksonUtils() throws Exception {
        throw new Exception("该类不能实例化") ;
    }

    public static String object2Str(Object o) {
        try {
            return objectMapper.writeValueAsString(o) ;
        }catch (JsonProcessingException e) {
            return null ;
        }
    }
}
