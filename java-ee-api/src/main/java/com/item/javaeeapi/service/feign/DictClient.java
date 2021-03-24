package com.item.javaeeapi.service.feign;

import com.item.javaee.entity.Dict;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @ClassName: DictClient
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-13 21:17
 * @Version: 1.0
 **/
@FeignClient("blog-client")
public interface DictClient {

    @RequestMapping(value = "/dict/articletype", method = RequestMethod.GET)
    public String getArticleType() ;
}
