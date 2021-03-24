package com.item.javaee.service;

import com.item.javaee.entity.Dict;
import com.item.javaee.repository.DictRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DictService
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 20:48
 * @Version: 1.0
 **/
public interface DictService {

    //根据字典码查找所有的字典项
    List<Dict> findAllByDictCode(String dictCode) ;
}
