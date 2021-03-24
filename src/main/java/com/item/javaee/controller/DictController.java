package com.item.javaee.controller;

import com.item.javaee.service.DictService;
import com.item.javaee.utils.JsonBuilder;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: DictController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 20:40
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/dict")
public class DictController {

    @Autowired
    DictService dictService ;

    @Value("${dict_article_type_code}")
    private String article_type_code ;
    @RequestMapping(value = "/articletype", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticleTypeList() {
        return JsonBuilder.buildJson(dictService.findAllByDictCode(article_type_code),
                200, "文章类型查询成功!") ;
    }

}
