package com.item.javaee.service.impl;

import com.item.javaee.entity.Tag;
import com.item.javaee.repository.TagRepository;
import com.item.javaee.service.TagService;
import com.item.javaee.vo.QueryVo;
import com.sun.xml.bind.v2.runtime.unmarshaller.TagName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TagServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-12 08:35
 * @Version: 1.0
 **/
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepository tagRepository ;
    @Override
    public Page<Tag> findPageTags(QueryVo queryVo) {
        Pageable pageable = PageRequest.of(queryVo.getPagenum() - 1, queryVo.getPagesize()) ;

        return tagRepository.findAll(pageable) ;
    }

    @Override
    public Tag createTag(Tag newTag) {
        return tagRepository.save(newTag) ;
    }

    @Override
    public List<Tag> getTagListByTagName(String tagName) {
        Tag tag = new Tag() ;
        tag.setTagName(tagName) ;
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("tagName", ExampleMatcher.GenericPropertyMatchers.startsWith()) ;

        Example<Tag> example = Example.of(tag, exampleMatcher) ;

        return this.tagRepository.findAll(example) ;
    }


    @Override
    public List<Tag> getAllTags() {
        return this.tagRepository.findAll() ;
    }
}
