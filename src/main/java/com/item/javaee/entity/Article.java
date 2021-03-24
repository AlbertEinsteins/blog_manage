package com.item.javaee.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ArticleView
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-04 19:30
 * @Version: 1.0
 **/
@Entity
@Table(name = "article")
@Data
public class Article implements Serializable {
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId ;


    @Column(name = "user_id")
    private Integer userId ;

    @Column(name = "sort_id")
    private Integer sortId ;


    //文章类型
    @Column(name = "article_type")
    private Integer dictId ;

    @Column(name = "article_title", length = 128)
    private String title ;

    @Column(name = "article_imgurl", length = 128)
    private String imgUrl ;

    @Column(name = "article_content")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content ;

    @Column(name = "article_html")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String html ;           //content 转化为 html

    @Column(name = "article_comment_num")
    private Integer commentNum ;

    @Column(name = "article_lookup_num")
    private Integer lookupNum ;

    @Column(name = "article_publishtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishTime ;

    /**
     * 0 -- 回收站
     * 1 -- 草稿
     * 3 -- 已发布
     */
    @Column(name = "article_status")
    private Integer status ;


    @Transient
    private Integer[] tagIds ;
}
