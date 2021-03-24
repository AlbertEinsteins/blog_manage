package com.item.javaeeapi.controller;

import com.item.javaee.entity.Sort;
import com.item.javaee.entity.Tag;
import com.item.javaeeapi.service.feign.TagClient;
import com.item.javaeeapi.vo.QueryVo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: TagController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 09:46
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/v1/tag")
public class TagController {

    @Autowired
    TagClient tagClient ;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getTagsByPage(QueryVo queryVo) {
        return tagClient.getPageableSorts(queryVo) ;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createSort(@RequestBody Tag newTag) {
        return tagClient.createSort(newTag) ;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String getTagListByName(@RequestParam("query") String query) {
        return tagClient.getListByTagName(query) ;
    }
}
