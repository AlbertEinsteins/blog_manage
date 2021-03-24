package com.item.javaee.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: Tag
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-04 19:57
 * @Version: 1.0
 **/
@Entity
@Table(name = "tag")
@Getter
@Setter
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId ;

    @Column(name = "tag_name", length = 32)
    private String tagName ;


}
