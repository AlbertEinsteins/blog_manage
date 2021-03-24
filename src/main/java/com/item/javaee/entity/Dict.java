package com.item.javaee.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: Dict
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 20:42
 * @Version: 1.0
 **/
@Entity
@Table(name = "dict")
public class Dict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer dictId ;

    @Column(name = "code")
    private String dictCode ;

    @Column(name = "name")
    private String dictName ;

    @Column(name = "value")
    private String dictValue ;

    public Dict() {
    }

    public Dict(String dictCode, String dictName, String dictValue) {
        this.dictCode = dictCode;
        this.dictName = dictName;
        this.dictValue = dictValue;
    }

    /*
        getter setter
        * */

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}
