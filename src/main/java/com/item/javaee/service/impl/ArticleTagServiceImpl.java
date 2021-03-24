package com.item.javaee.service.impl;

import com.item.javaee.entity.ArticleTag;
import com.item.javaee.repository.ArticleTagRepository;
import com.item.javaee.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ArticleTagServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 17:28
 * @Version: 1.0
 **/
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    ArticleTagRepository articleTagRepository ;

    @Override
    public ArticleTag create(ArticleTag articleTag) {
        return articleTagRepository.save(articleTag) ;
    }

    @Override
    public void deleteById(Integer id) {
        this.articleTagRepository.deleteById(id) ;
    }

    @Override
    public void deleteByArticleId(Integer article_id) {
        ArticleTag articleTag = new ArticleTag() ;
        articleTag.setArticleId(article_id);

        Example<ArticleTag> example = Example.of(articleTag) ;


        //查询文章的所有的标签，删除
        this.articleTagRepository.deleteInBatch(this.articleTagRepository.findAll(example));
    }
}
