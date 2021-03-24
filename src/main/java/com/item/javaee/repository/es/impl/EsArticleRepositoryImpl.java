package com.item.javaee.repository.es.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.item.javaee.entity.Article;
import com.item.javaee.repository.es.EsArticleRepository;
import com.item.javaee.utils.JacksonUtils;
import com.item.javaee.vo.EsPage;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @ClassName: EsArticleRepositoryImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-20 21:00
 * @Version: 1.0
 **/
@Repository
public class EsArticleRepositoryImpl implements EsArticleRepository {

    private static final Logger LOG = LoggerFactory.getLogger(EsArticleRepositoryImpl.class);


    @Resource
    RestHighLevelClient restHighLevelClient;

    @Resource
    ObjectMapper mapper;

    @Resource
    Converter dateConverter;

    @Value("${article.index.name}")
    private String article_index_name;

    @Override
    public boolean createDoc(Article article) {
        IndexRequest indexRequest = new IndexRequest(this.article_index_name);
        //设置元数据
        indexRequest.id(article.getArticleId().toString());
        try {
            indexRequest.source(mapper.writeValueAsString(article), XContentType.JSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //异步执行
        this.restHighLevelClient.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
            }

            @Override
            public void onFailure(Exception e) {
                LOG.info("添加文档出现异常: " + article);
            }
        });
        return true;
    }

    @Override
    public boolean updateDoc(Article article) {
        IndexRequest updateRequest = new IndexRequest(article_index_name) ;

        updateRequest.id(article.getArticleId().toString());
        try {
            updateRequest.source(mapper.writeValueAsString(article), XContentType.JSON);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        this.restHighLevelClient.indexAsync(updateRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse updateResponse) {
                LOG.info("update sucess");
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace() ;
                LOG.info("更新文档出现异常: " + article);
            }
        });
        return true;
    }

    @Override
    public boolean deleteDoc(Integer id) {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.id(id.toString());

        this.restHighLevelClient.deleteAsync(deleteRequest, RequestOptions.DEFAULT, new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
            }

            @Override
            public void onFailure(Exception e) {
                LOG.info("删除文档异常: " + id);
            }
        });
        return true;
    }

    @Override
    public Article findById(Integer id) {
        GetRequest getRequest = new GetRequest();
        getRequest.id(id.toString());
        getRequest.index(this.article_index_name);

        Article article = new Article();

        try {
            GetResponse response = this.restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            Map<String, Object> sourceAsMap = response.getSourceAsMap();

            //注册日期转化器类
            ConvertUtils.register(dateConverter, Date.class);
            BeanUtils.populate(article, sourceAsMap);
            return article;
        } catch (IOException e) {
            LOG.info("获取文章失败，IO异常");
            return article;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return article;
        }
    }

    @Override
    public boolean createBulkDoc(Iterable<Article> articles) {
        BulkRequest bulkRequest = new BulkRequest();

        articles.forEach(item -> {
            IndexRequest request = new IndexRequest(this.article_index_name);
            try {
                request.id(item.getArticleId().toString());
                request.source(mapper.writeValueAsString(item), XContentType.JSON);

                bulkRequest.add(request);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        this.restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
                LOG.info("批量创建失败");
            }
        });
        return true;
    }

    @Override
    public EsPage<Article> getListBySortId(Integer sid, Integer pagenum, Integer pagesize) {

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置分页条件
        sourceBuilder.from((pagenum - 1) * pagesize);
        sourceBuilder.size(pagesize);
        sourceBuilder.query(QueryBuilders.matchQuery("sortId", sid));

        //将请求条件设置给请求
        return this.getListByCondition(sourceBuilder);
    }

    @Override
    public EsPage<Article> getListByTagId(Integer tid, Integer pagenum, Integer pagesize) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置分页条件
        sourceBuilder.from((pagenum - 1) * pagesize);
        sourceBuilder.size(pagesize);
        sourceBuilder.query(QueryBuilders.matchQuery("tagId", tid));

        //将请求条件设置给请求
        return this.getListByCondition(sourceBuilder);

    }

    //根据页号，页大小，条件，返回(元素 | total)
    private EsPage<Article> getListByCondition(SearchSourceBuilder builder) {
        EsPage<Article> page = new EsPage<>();
        List<Article> articles = new ArrayList<>();
        long total = 0L;

        SearchRequest searchRequest = new SearchRequest();

        searchRequest.indices(this.article_index_name);
        searchRequest.source(builder);

        try {
            SearchResponse response = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            total = response.getHits().getTotalHits().value;

            Arrays.stream(response.getHits().getHits()).forEach(item -> {
                Article article = new Article();
                try {

                    ConvertUtils.register(dateConverter, Date.class);
                    BeanUtils.populate(article, item.getSourceAsMap());
                    articles.add(article);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });

            page.setTotal(total);
            page.setElements(articles);
            return page;
        } catch (IOException e) {
            e.printStackTrace();
            return page;
        }
    }

}
