package com.item.javaee.vo;

import com.item.javaee.entity.Article;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: EsPage
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-22 14:52
 * @Version: 1.0
 **/
@Data
public class EsPage<T> {
    private List<T> elements ;
    private Long total ;
}
