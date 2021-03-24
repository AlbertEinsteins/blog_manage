package com.item.javaee.service;

import com.item.javaee.entity.Article;
import com.item.javaee.entity.view.ArticleView;
import com.item.javaee.vo.ArticleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    //默认是static final的
    Integer DEFAULT_USER_ID = 3 ;

    /**
     * 0 -- 回收站
     * 1 -- 草稿
     * 3 -- 已发布
     */
    Integer ARTICLE_STATUS_RECYCLE = 0 ;
    Integer ARTICLE_STATUS_DRAFT = 1 ;
    Integer ARTICLE_STATUS_PUBLISHED = 3 ;

    //新建一篇文章
    Article createArticle(Article article) ;

    //分页查询部分文章属性
    Page<ArticleView> getPageableArticle(ArticleQueryVo vo) ;

    //根据id查找
    Article findById(Integer id) ;

    //根据id修改
    Article updateById(Article article) ;

    //统计
    Long countAll() ;

    //根据分类id从es分页查询文章
//    List<Article> findPagableArticleFromEs(Integer sid, Integer pagenum, Integer pagesize) ;

    //更新commentNum,lookupNum
    Article updateCommentIncre(Integer aid) ;

    Article updateLookUpNumIncre(Integer aid) ;
}
