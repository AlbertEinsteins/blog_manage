package com.item.javaee.redis;

import com.item.javaee.entity.Sort;
import com.item.javaee.repository.SortRepository;
import com.item.javaee.service.SortService;
import com.item.javaee.utils.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

/**
 * @ClassName: Demo1
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-21 12:04
 * @Version: 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Demo1 {

    @Resource
    RedisClient redisClient ;


    @Resource
    SortService sortService ;

    @Test
    public void testSearch() {

        List<Sort> list = sortService.getAllSortsByTree() ;

        this.redisClient.set("javaee:index:sortTree", list) ;

        Object o = this.redisClient.get("javaee:index:sortTree") ;

        System.out.println(o);
    }


    @Value("${javaee.index.sortTree}")
    private String sortKey ;

    @Test
    public void delete() {
        this.redisClient.del(sortKey) ;
    }
}
