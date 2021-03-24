package com.item.javaeeapi.controller;

import com.item.javaee.entity.Sort;
import com.item.javaeeapi.service.feign.SortClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: SortController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-10 17:53
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/v1/sort")
public class SortController {

    @Autowired
    SortClient sortClient;
    //获取
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getSorts(
            @RequestParam(name = "type", defaultValue = "list", required = false) String type) {
        return sortClient.getSortsByType(type) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getSortById(@PathVariable("id") Integer id) {
        return sortClient.getSortById(id) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateSort(@PathVariable("id") Integer id, @RequestBody Sort sort) {
        return sortClient.updateSort(id, sort) ;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createSort(@RequestBody Sort sort) {
        return sortClient.createSort(sort) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteSort(@PathVariable("id") Integer sid) {
        return sortClient.deleteSortById(sid) ;
    }
}
