package com.item.javaee.controller;

import com.item.javaee.entity.Sort;
import com.item.javaee.service.SortService;
import com.item.javaee.utils.JsonBuilder;
import com.item.javaee.utils.RedisClient;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: SortController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-10 13:56
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/sort")
public class SortController {

    @Resource
    SortService sortService ;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getSorts(@RequestParam(name = "type", defaultValue = "list",
            required = false) String type) {
        if("tree".equals(type)) {
            return JsonBuilder.buildJson(this.sortService.getAllSortsByTree(), 200, "树形分类列表获取成功!") ;
        }
        else {
            return JsonBuilder.buildJson(this.sortService.getAllSortsByList(), 200, "分类列表获取成功!") ;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getSortById(@PathVariable("id") Integer sid) {
        return JsonBuilder.buildJson(this.sortService.getById(sid), 200, "获取成功!") ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Object updateSort(@RequestBody Sort sort) {
        return JsonBuilder.buildJson(this.sortService.updateById(sort), 200, "修改成功！") ;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object saveSort(@RequestBody Sort sort) {
        return JsonBuilder.buildJson(this.sortService.createSort(sort), 200, "添加成功!") ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteSort(@PathVariable("id") Integer sid) {
        Sort existSort = this.sortService.getById(sid) ;
        if(existSort != null && existSort.getSortLevel() == 1) {
            return JsonBuilder.buildJson(existSort, 301, "删除失败,不允许删除一级分类!只能禁用") ;
        }

        this.sortService.deleteSort(sid) ;
        return JsonBuilder.buildJson(null, 200, "删除成功!") ;
    }

}
