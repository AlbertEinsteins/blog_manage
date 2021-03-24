package com.item.javaee.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @ClassName: ArticleTag
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 17:08
 * @Version: 1.0
 **/
@Entity
@Table(name = "article_tag")
@Data
public class ArticleTag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "article_Id")
    private Integer articleId ;

    @Column(name = "tag_id")
    private Integer tagId ;
}
