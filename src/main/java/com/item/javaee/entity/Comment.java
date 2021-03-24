package com.item.javaee.entity;

import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Comment
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-04 19:56
 * @Version: 1.0
 **/
@Entity
@Table(name = "comment")
@Data
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId ;

    @Column(name = "comment_name")
    private String name ;

    @Column(name = "comment_content")
    private String desc ;

    @Column(name = "comment_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentTime ;


    @Column(name = "article_id")
    private Integer articleId ;

    @Column(name = "comment_level")
    private Integer level ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_pid")
    private Comment parent ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Comment> childs ;




    //用来接收前台发来的临时数据
    @Transient
    private Integer parentId ;

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", commentTime=" + commentTime +
                ", articleId=" + articleId +
                ", childs=" + childs +
                '}';
    }
}
