package com.item.javaee.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.item.javaee.entity.Article;
import com.item.javaee.repository.ArticleRepository;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkAction;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
;

/**
 * @ClassName: Demo1
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-16 12:11
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo1 {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void demo1() throws IOException {
        GetRequest request = new GetRequest("test_index", "test_type2", "3");


        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);


        System.out.println(response);
    }

    @Test
    public void getAll() throws IOException {
        SearchRequest srq = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        srq.indices("test_index");
        srq.source(sourceBuilder);

        System.out.println(srq.source());
        SearchResponse resp = restHighLevelClient.search(srq, RequestOptions.DEFAULT);

    }

    @Test
    public void getDateByPage() throws IOException {
        String key = "测试";

        SearchRequest req = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        req.indices("test_article2");

        //分页  2条,   查找第一页的数据
        sourceBuilder.query(QueryBuilders.matchQuery("title", key))
                .from(0).size(2);

        req.source(sourceBuilder);

        System.out.println(req.source());

        SearchResponse resp = restHighLevelClient.search(req, RequestOptions.DEFAULT);

        Arrays.stream(resp.getHits().getHits()).forEach(item -> {
            Article article = new Article();
            BeanUtils.copyProperties(item.getSourceAsMap(), article);

            System.out.println(article);
        });

    }

    @Resource
    ArticleRepository articleRepository;

    @Resource
    ObjectMapper mapper;

    @Test
    public void insertArticle() throws IOException {
        List<Article> all = articleRepository.findAll();

        System.out.println(all.get(0).getPublishTime());
        BulkRequest bulkRequest = new BulkRequest();

        all.forEach(item -> {
            IndexRequest request = new IndexRequest("test_article2");
            try {
                request.id(item.getArticleId().toString());
                request.source(mapper.writeValueAsString(item), XContentType.JSON);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            bulkRequest.add(request);
        });


        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        RestStatus status = bulk.status();
    }

    @Test
    public void searchArticle() throws IOException {
        SearchRequest sreq = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sreq.indices("test_article2");
        //BUG

//        sourceBuilder.query(matchQueryBuilder) ;
        sourceBuilder.fetchSource(null, "html");

        sreq.source(sourceBuilder);

        SearchResponse search = restHighLevelClient.search(sreq, RequestOptions.DEFAULT);

        Arrays.stream(search.getHits().getHits()).forEach(item -> {
            System.out.println(item.getSourceAsMap());
        });
    }


    //根据id查找
    @Test
    public void findById() throws IOException, InvocationTargetException, IllegalAccessException {
        GetRequest getRequest = new GetRequest("test_article2");

        getRequest.id("2");

        GetResponse response = this.restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        Article article = new Article();

        //为BeanUtils注册一个日期转化器
        ConvertUtils.register(dateConverter, Date.class);

        System.out.println(response.getSourceAsMap().get("publishTime"));
        org.apache.commons.beanutils.BeanUtils.populate(article, response.getSourceAsMap());

        System.out.println(article);
    }

    @Test
    public void testDate() throws ParseException {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ");

        Date parse = df1.parse("2019-12-16T15:16:09.000+0000");
    }


    @Resource
    Converter dateConverter ;


}