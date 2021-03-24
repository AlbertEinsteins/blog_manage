package com.item.javaeeapi.controller;

import com.item.javaee.entity.Dict;
import com.item.javaeeapi.service.feign.DictClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: DictController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-13 21:21
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/v1/dict")
public class DictController {

    @Autowired
    DictClient dictClient ;

    @RequestMapping(value = "/articletype", method = RequestMethod.GET)
    public String getArticleType() {
        return dictClient.getArticleType() ;
    }

}
