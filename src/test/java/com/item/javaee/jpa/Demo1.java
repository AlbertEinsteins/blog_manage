package com.item.javaee.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.item.javaee.entity.Article;
import com.item.javaee.entity.ArticleTag;
import com.item.javaee.repository.ArticleRepository;
import com.item.javaee.repository.ArticleTagRepository;
import com.netflix.discovery.converters.Auto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @ClassName: Demo1
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 15:24
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo1 {

    @Autowired
    ArticleRepository articleRepository ;

    @Autowired
    ArticleTagRepository articleTagRepository ;

    @Autowired
    EntityManager entityManager ;
    @Test
    public void search() {

        articleTagRepository.findAll().forEach(System.out::println) ;

    }

    @Test
    @Transactional
    @Rollback(false)
    public void insert() {
        ArticleTag articleTag = new ArticleTag() ;

        articleTag.setArticleId(1) ;
        articleTag.setTagId(2);

        articleTagRepository.save(articleTag) ;


    }


    @Autowired
    ObjectMapper objectMapper ;

    @Test
    public void testMapper() throws JsonProcessingException {
        Integer[] a = new Integer[]{1,2,3} ;
        String s = objectMapper.writeValueAsString(a);


        Integer[] integers = objectMapper.readValue(s, Integer[].class);

        System.out.println(integers) ;
    }


}
