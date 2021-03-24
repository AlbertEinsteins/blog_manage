package com.item.javaeerabbitmq.domain;

import java.io.Serializable;

/**
 * @ClassName: QueryVo
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-07 20:26
 * @Version: 1.0
 **/
public class QueryVo implements Serializable {
    private String from ;
    private String to ;

    public QueryVo() {
    }

    public QueryVo(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
