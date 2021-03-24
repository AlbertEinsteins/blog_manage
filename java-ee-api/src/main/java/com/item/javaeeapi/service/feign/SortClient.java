package com.item.javaeeapi.service.feign;

import com.item.javaee.entity.Sort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("blog-client")
public interface SortClient {

    @RequestMapping(value = "/sort/list", method = RequestMethod.GET)
    public String getSortsByType(
            @RequestParam(value = "type", required = false, defaultValue = "list") String type) ;

    @RequestMapping(value = "/sort/{id}", method = RequestMethod.PUT)
    public String updateSort(@PathVariable("id") Integer id, @RequestBody Sort sort) ;

    @RequestMapping(value = "/sort/{id}", method = RequestMethod.GET)
    public String getSortById(@PathVariable("id") Integer sid) ;


    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public String createSort(@RequestBody Sort sort) ;

    @RequestMapping(value = "/sort/{id}", method = RequestMethod.DELETE)
    public String deleteSortById(@PathVariable("id") Integer id) ;
}
