package com.item.javaee.vo;

import lombok.Data;

/**
 * @ClassName: QueryVo
 * @Description TODO, 封装请求的查询条件
 * @Author: jff
 * @Date: 2019-12-03 15:58
 * @Version: 1.0
 **/
@Data
public class QueryVo {
    private Integer pagenum ;
    private Integer pagesize ;
    private String key ;

}
