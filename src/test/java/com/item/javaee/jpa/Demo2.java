package com.item.javaee.jpa;

import com.item.javaee.entity.view.ArticleView;
import com.item.javaee.repository.ArticleViewRepository;
import com.item.javaee.service.ArticleService;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName: Demo2
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-16 23:08
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo2 {

    @Autowired
    ArticleService articleService ;

    @Autowired
    ArticleViewRepository articleViewRepository ;

    @Test
    public void testSelect() {



    }
}
