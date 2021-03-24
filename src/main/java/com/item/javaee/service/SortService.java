package com.item.javaee.service;

import com.item.javaee.entity.Sort;

import java.util.List;

public interface SortService {
    //以树形获取所有的分类
    List<Sort> getAllSortsByTree() ;

    //根据pid查询
    List<Sort> getByPid(Integer pid) ;

    //以列表形式获取所有的分类
    List<Sort> getAllSortsByList() ;

    //根据id查询
    Sort getById(Integer id) ;

    //更新sort
    Sort updateById(Sort newSort) ;

    //添加一个新的分类
    Sort createSort(Sort newSort) ;

    //根据id删除一个分类
    void deleteSort(Integer sid) ;

    //统计分类个数
    Long countAll() ;
}
