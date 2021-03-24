package com.item.javaee.service;

import com.item.javaee.entity.Tag;
import com.item.javaee.vo.QueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagService {
    //分页查找
    Page<Tag> findPageTags(QueryVo queryVo) ;

    //添加一个标签
    Tag createTag(Tag newTag) ;

    //根据标签名模糊查询
    List<Tag> getTagListByTagName(String tagName) ;

    List<Tag> getAllTags() ;
}
