package com.item.javaeeapi.controller;

import com.item.javaee.entity.Article;
import com.item.javaeeapi.service.feign.ArticleClient;
import com.item.javaeeapi.vo.ArticleQueryVo;
import com.item.javaeeapi.vo.QueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ArticleController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 17:47
 * @Version: 1.0
 **/
@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    @Autowired
    ArticleClient articleClient ;

    @RequestMapping(method = RequestMethod.POST)
    public String createArticle(@RequestBody Article article) {
        return articleClient.createArticle(article) ;
    }


    @ApiOperation(value = "从数据库中查询文章的部分数据", notes = "具体的数据请查阅返回的json格式")
    @RequestMapping(value = "/views", method = RequestMethod.GET)
    public String getPageableArticleList(ArticleQueryVo vo) {
        return this.articleClient.getPageableArticle(vo) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id) {
        return this.articleClient.getArticleById(id) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateById(@PathVariable("id") Integer id, @RequestBody Article article) {
        return this.articleClient.updateById(id, article) ;
    }

}
