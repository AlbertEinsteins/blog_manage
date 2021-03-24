package com.item.javaee.service;


import com.item.javaee.entity.ArticleTag;

import java.util.List;

public interface ArticleTagService {
    //添加一项
    ArticleTag create(ArticleTag articleTag) ;

    //根据id删除一项
    void deleteById(Integer id) ;


    //根据articleId删除一系列关系
    void deleteByArticleId(Integer article_id) ;
}
