package com.item.javaeeapi.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: ArticleQueryVo
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-17 00:03
 * @Version: 1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleQueryVo extends QueryVo {

    /**
     * 文章的类型
     */
    private Integer typeId ;

    /**
     * 文章状态
     */
    private Integer status ;
}
