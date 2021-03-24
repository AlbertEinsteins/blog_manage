package com.item.javaee.service.impl;

import com.item.javaee.entity.Article;
import com.item.javaee.entity.view.ArticleView;
import com.item.javaee.repository.ArticleRepository;
import com.item.javaee.repository.ArticleViewRepository;
import com.item.javaee.repository.es.EsArticleRepository;
import com.item.javaee.service.ArticleService;
import com.item.javaee.vo.ArticleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArticleServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-14 23:49
 * @Version: 1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleRepository articleRepository ;

    @Resource
    ArticleViewRepository articleViewRepository ;

    @Resource
    EsArticleRepository esArticleRepository ;

    @Override
    public Article createArticle(Article article) {
        //默认文章用户的拥有者
        article.setUserId(ArticleService.DEFAULT_USER_ID) ;

        //新建文章的评论数和查阅数为0
        article.setCommentNum(0) ;
        article.setLookupNum(0) ;
        article.setPublishTime(new Date()) ;


        //异步添加到elasticsearch
        esArticleRepository.createDoc(article) ;

        return articleRepository.save(article) ;
    }


    /**
     * 查询文章视图
     * @param vo
     * @return
     */
    @Override
    public Page<ArticleView> getPageableArticle(ArticleQueryVo vo) {
        PageRequest pageRequest = PageRequest.of(vo.getPagenum() - 1, vo.getPagesize()) ;

        ArticleView articleView = new ArticleView() ;
        articleView.setTypeId(vo.getTypeId()) ;
        articleView.setStatus(vo.getStatus()) ;
        articleView.setTitle(vo.getKey()) ;

        /*
        * 查找%title% 并且typeId 的值
        * */
        ExampleMatcher matcher =
                ExampleMatcher.matching()
                    .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains()) ;

        Example<ArticleView> example = Example.of(articleView, matcher) ;
        return this.articleViewRepository.findAll(example, pageRequest) ;
    }

    @Override
    public Article findById(Integer id) {
        return this.articleRepository.findById(id).orElse(new Article()) ;
    }

    public Article updateById(Article article) {
        Article exArticle = this.articleRepository.findById(article.getArticleId()).orElse(new Article()) ;

        BeanUtils.copyProperties(article, exArticle) ;

        //更新es文档
        this.esArticleRepository.updateDoc(article) ;

        return this.articleRepository.save(exArticle) ;
    }

    @Override
    public Long countAll() {
        return this.articleRepository.count() ;
    }

    //TODO,未完成,也不需要完成
//    @Override
//    public List<Article> findPagableArticleFromEs(Integer sid, Integer pagenum, Integer pagesize) {
//        return null ;
//    }


    @Override
    public Article updateCommentIncre(Integer aid) {
        Article exist = this.findById(aid) ;
        exist.setCommentNum(exist.getCommentNum() + 1) ;
        Article rtn = this.articleRepository.save(exist) ;

        this.esArticleRepository.updateDoc(exist) ;

        return rtn ;
    }

    @Override
    public Article updateLookUpNumIncre(Integer aid) {
        Article exist = this.findById(aid) ;
        exist.setLookupNum(inc(exist.getLookupNum())) ;

        Article rtn = this.articleRepository.save(exist) ;
        this.esArticleRepository.updateDoc(exist) ;
        return rtn ;
    }

    private Integer inc(Integer num) {
        return num + 1 ;
    }
}
