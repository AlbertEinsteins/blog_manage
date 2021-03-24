package com.item.javaeeapi.service.feign;

import com.item.javaee.entity.Tag;
import com.item.javaeeapi.vo.QueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("blog-client")
public interface TagClient {

    @RequestMapping(value = "/tag/page", method = RequestMethod.GET)
    public String getPageableSorts(@RequestBody QueryVo queryVo) ;

    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public String createSort(@RequestBody Tag newTag) ;

    @RequestMapping(value = "/tag/query", method = RequestMethod.GET)
    public String getListByTagName(@RequestParam("query") String query) ;
}
