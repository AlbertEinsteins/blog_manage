package com.item.javaee.service.impl;

import com.item.javaee.entity.Comment;
import com.item.javaee.repository.CommentRepository;
import com.item.javaee.service.CommentService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: CommentServiceImpl
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-24 18:22
 * @Version: 1.0
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentRepository commentRepository ;


    //保存
    public Comment saveComment(Comment comment, Integer pid) {
        comment.setCommentTime(new Date());

        //如果提交上来的父级id不存在，表示这是第一级评论
        //如果存在那么需要查找这个id对应的parent,然后设置给son
        if(pid != null) {
            Comment parent = this.commentRepository.findById(comment.getParentId()).orElse(new Comment()) ;
            comment.setParent(parent) ;
        }

        return this.commentRepository.save(comment) ;
    }

    @Override
    public List<Comment> getListByTree(Integer articleId) {
        Comment condition = new Comment() ;
        condition.setArticleId(articleId) ;
        //一级评论，表示第一层评论
        condition.setLevel(1) ;

        //根据日期降序排列
        Sort sort = Sort.by(Sort.Direction.DESC, "commentTime") ;
        Example<Comment> example = Example.of(condition) ;

        return this.commentRepository.findAll(example, sort) ;
    }
}
