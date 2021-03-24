package com.item.javaeeapi.service.feign;

import com.item.javaee.entity.Article;
import com.item.javaeeapi.vo.ArticleQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: ArticleClient
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 17:47
 * @Version: 1.0
 **/
@FeignClient("blog-client")
public interface ArticleClient {

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String createArticle(@RequestBody Article article) ;


    /**
     * get 请求携带请求体，会自动转化为post请求
     * @param vo
     * @return
     */
    @RequestMapping(value = "/article/views", method = RequestMethod.GET)
    public String getPageableArticle(@RequestBody ArticleQueryVo vo) ;


    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String getArticleById(@PathVariable("id") Integer id) ;

    @RequestMapping(value = "/article/{id}", method = RequestMethod.PUT)
    public String updateById(@PathVariable("id") Integer id, @RequestBody Article article) ;

}
