package com.item.javaee.entity.view;

import com.item.javaee.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: ArticleView
 * @Description 数据库Article表的视图
 * @Author: jff
 * @Date: 2019-12-16 23:01
 * @Version: 1.0
 **/

@Table(name = "article_view")
@Entity
@Data
public class ArticleView implements Serializable {
    @Id
    @Column(name = "article_id")
    private Integer id ;

    @Column(name = "article_status")
    private Integer status ;

    @Column(name = "article_type")
    private Integer typeId ;

    @Column(name = "article_title")
    private String title ;

    @Column(name = "article_comment_num")
    private Integer cnum ;

    @Column(name = "article_lookup_num")
    private Integer lnum ;

    @Column(name = "article_publishtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime ;
}
