package com.item.javaee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.javaee.entity.Article;
import com.item.javaee.entity.ArticleTag;
import com.item.javaee.entity.view.ArticleView;
import com.item.javaee.repository.ArticleRepository;
import com.item.javaee.repository.ArticleViewRepository;
import com.item.javaee.service.ArticleService;
import com.item.javaee.service.ArticleTagService;
import com.item.javaee.utils.JsonBuilder;
import com.item.javaee.vo.ArticleQueryVo;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ArticleController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 17:29
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleTagService articleTagService ;

    @Autowired
    ArticleService articleService ;

    /************************************************
     * 提供json 接口
     */

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object createArticle(@RequestBody Article article) {

        if(article.getTagIds() != null && article.getTagIds().length != 0) {
            //保存文章
            Article insertedArticle = this.articleService.createArticle(article);

            //添加文章和标签的关联关系
            Arrays.asList(article.getTagIds()).forEach(tagId -> {
                ArticleTag articleTag = new ArticleTag() ;
                articleTag.setArticleId(insertedArticle.getArticleId()) ;
                articleTag.setTagId(tagId) ;

                this.articleTagService.create(articleTag) ;
            }) ;

            return JsonBuilder.buildJson(null, 200, "文章发布成功") ;
        }
        return JsonBuilder.buildJson(this.articleService.createArticle(article), 200, "文章发布成功!") ;
    }


    @ApiOperation(value = "返回文章的部分属性数据", notes = "只返回了文章的一个子集视图")
    @RequestMapping(value = "/views", method = RequestMethod.POST)
    @ResponseBody
    public Object getPageableArticleList(@RequestBody ArticleQueryVo vo) {
        Map<String, Object> data = new HashMap<>();

        Page<ArticleView> viewPage = this.articleService.getPageableArticle(vo) ;
        vo.setPagenum(viewPage.getNumber() + 1) ;
        data.put("data", viewPage.getContent()) ;
        data.put("total", viewPage.getTotalElements()) ;
        data.put("articleQueryVo", vo) ;


        return JsonBuilder.buildJson(data, 200, "获取成功!") ;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getById(@PathVariable("id") Integer id) {
        return JsonBuilder.buildJson(this.articleService.findById(id), 200, "成功!") ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Object updateById(@RequestBody Article article) {
        this.articleTagService.deleteByArticleId(article.getArticleId()) ;

        if(article.getTagIds() == null
                || (article.getTagIds() != null && article.getTagIds().length == 0)) {
            return JsonBuilder.buildJson(this.articleService.updateById(article), 200, "修改成!") ;
        }

        Arrays.asList(article.getTagIds()).forEach(tagId -> {
            ArticleTag articleTag = new ArticleTag() ;
            articleTag.setArticleId(article.getArticleId()) ;
            articleTag.setTagId(tagId) ;

            this.articleTagService.create(articleTag) ;
        }) ;

        return JsonBuilder.buildJson(this.articleService.updateById(article), 200, "成功!") ;
    }

}
