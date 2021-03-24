package com.item.javaeeapi.vo;

import java.io.Serializable;

/**
 * @ClassName: QueryVo
 * @Description TODO, 封装请求的查询条件
 * @Author: jff
 * @Date: 2019-12-03 15:58
 * @Version: 1.0
 **/
public class QueryVo implements Serializable {
    private Integer pagenum ;
    private Integer pagesize ;
    private String key ;

    public QueryVo() {
    }

    public QueryVo(Integer pagenum, Integer pagesize, String key) {
        this.pagenum = pagenum;
        this.pagesize = pagesize;
        this.key = key;
    }

    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
