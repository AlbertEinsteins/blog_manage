package com.item.javaee.service.impl;

import com.item.javaee.entity.Sort;
import com.item.javaee.repository.SortRepository;
import com.item.javaee.service.SortService;
import com.item.javaee.utils.RedisClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: SortServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-10 13:38
 * @Version: 1.0
 **/
@Service
public class SortServiceImpl implements SortService {

    @Autowired
    SortRepository sortRepository ;

    @Value("${javaee.index.sortTree}")
    private String sortKey ;

    @Resource
    RedisClient redisClient ;

    @Override
    @SuppressWarnings("unchecked")
    public List<Sort> getAllSortsByTree() {
        //查询PID == 0 的item
        Object sortValue = this.redisClient.get(sortKey) ;
        if(sortValue == null) {
            List<Sort> parentList = this.getByPid(0);
            parentList.forEach(sort -> {
                List<Sort> childs = this.getByPid(sort.getSortId());

                sort.setChildren(childs);
            });

            this.redisClient.set(sortKey, parentList) ;
        }


        return (List<Sort>) sortValue ;
    }

    @Override
    public List<Sort> getByPid(Integer pid) {

        //设置查询条件
        Sort sort = new Sort() ;
        sort.setSortPid(pid) ;
        Example<Sort> example = Example.of(sort) ;

        return this.sortRepository.findAll(example) ;
    }

    @Override
    public List<Sort> getAllSortsByList() {
        return this.sortRepository.findAll() ;
    }

    @Override
    public Sort getById(Integer id) {
        Optional<Sort> sort = this.sortRepository.findById(id);
        return sort.orElse(null) ;
    }


    @Override
    public Sort updateById(Sort newSort) {


        Sort existSort = this.sortRepository.findById(newSort.getSortId()).orElse(new Sort()) ;
        BeanUtils.copyProperties(newSort, existSort) ;
        Sort s = this.sortRepository.save(existSort) ;
        updateSortTreeInRedis() ;
        return s ;
    }

    @Override
    public Sort createSort(Sort newSort) {
        Sort s = this.sortRepository.save(newSort) ;
        updateSortTreeInRedis() ;
        return s ;
    }


    @Override
    public void deleteSort(Integer sid) {
        this.sortRepository.deleteById(sid) ;
        updateSortTreeInRedis() ;
    }


    @Override
    public Long countAll() {
        return this.sortRepository.count() ;
    }


    //更新redis中的树形列表
    private void updateSortTreeInRedis() {
        this.redisClient.set(sortKey, this.getAllSortsByTree()) ;
    }
}
