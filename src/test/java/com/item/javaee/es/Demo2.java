package com.item.javaee.es;

import com.item.javaee.entity.Article;
import com.item.javaee.repository.ArticleRepository;
import com.item.javaee.repository.es.EsArticleRepository;
import com.item.javaee.vo.EsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: Demo2
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-22 12:40
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo2 {

    @Resource
    EsArticleRepository esArticleRepository ;

    @Resource
    ArticleRepository articleRepository ;

    @Test
    public void testSearch() {

        Integer sid = 4 ;
        EsPage<Article> listBySortId = esArticleRepository.getListBySortId(sid, 1, 5);

        System.out.println(listBySortId.getTotal()) ;
    }

    @Test
    @Transactional
    public void testInsert() {
        esArticleRepository.createBulkDoc(this.articleRepository.findAll()) ;
    }

    @Test
    @Transactional
    public void insert() {
        Article article = this.articleRepository.findById(2).orElse(new Article()) ;

        this.esArticleRepository.createDoc(article) ;
    }
}
