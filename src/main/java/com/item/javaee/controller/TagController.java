package com.item.javaee.controller;

import com.item.javaee.entity.Tag;
import com.item.javaee.service.TagService;
import com.item.javaee.utils.JsonBuilder;
import com.item.javaee.vo.QueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TagController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 08:49
 * @Version: 1.0
 **/
@Api(value = "tag-api", description = "这里是标签的接口详细信息")
@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService ;

    @ApiOperation(value = "分页获取标签列表", notes = "后台上传 { pagenum: 1, pagesize: " +
            "2, key: 'xxx' } 这样的json对象, 需要注意的是第一页是pagenum=1")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    @ResponseBody
    public Object findTagsByPage(@RequestBody QueryVo queryVo) {
        Map<String, Object> data = new HashMap<>() ;

        Page<Tag> pageTags = this.tagService.findPageTags(queryVo);

        //更新分页的元数据
        queryVo.setPagenum(pageTags.getNumber() + 1) ;
        data.put("queryVo", queryVo) ;

        //分页的真实数据
        data.put("data", pageTags.getContent()) ;
        data.put("total", pageTags.getTotalElements()) ;
        return JsonBuilder.buildJson(data, 200, "获取成功!");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object createTag(@RequestBody Tag tag) {
        return JsonBuilder.buildJson(this.tagService.createTag(tag), 200, "添加成功!") ;
    }


    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public Object getByTagName(@RequestParam("query") String query) {
        return JsonBuilder.buildJson(this.tagService.getTagListByTagName(query), 200, "搜索成功!") ;
    }
}
