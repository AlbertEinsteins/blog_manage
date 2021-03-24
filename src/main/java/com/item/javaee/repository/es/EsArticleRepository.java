package com.item.javaee.repository.es;

import com.item.javaee.entity.Article;
import com.item.javaee.entity.ArticleTag;
import com.item.javaee.vo.EsPage;

import java.util.List;

/**
 * @ClassName: EsArticleRepository
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-20 20:49
 * @Version: 1.0
 **/
public interface EsArticleRepository {
    //添加文档
    boolean createDoc(Article article) ;

    //更新文档
    boolean updateDoc(Article article) ;

    //删除文档
    boolean deleteDoc(Integer id) ;

    //查询文档, 所有字段
    Article findById(Integer id) ;

    //批量保存文档
    boolean createBulkDoc(Iterable<Article> articles) ;

    //根据分类id查询
    EsPage<Article> getListBySortId(Integer sid, Integer pagenum, Integer pagesize) ;

    //根据标签id查询
    EsPage<Article> getListByTagId(Integer tid, Integer pagenum, Integer pagesize) ;
}
