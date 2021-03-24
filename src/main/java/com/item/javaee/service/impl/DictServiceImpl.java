package com.item.javaee.service.impl;

import com.item.javaee.entity.Dict;
import com.item.javaee.repository.DictRepository;
import com.item.javaee.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: DictServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 20:53
 * @Version: 1.0
 **/
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    DictRepository dictRepository ;

    @Override
    public List<Dict> findAllByDictCode(String dictCode) {
        Dict dict = new Dict() ;
        dict.setDictCode(dictCode) ;

        Example<Dict> example = Example.of(dict) ;
        return this.dictRepository.findAll(example) ;
    }
}
