package com.item.javaee.service;

import com.item.javaee.entity.Comment;

import java.util.List;

/**
 * @ClassName: CommentService
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-24 18:22
 * @Version: 1.0
 **/
public interface CommentService {

    //保存
    Comment saveComment(Comment comment, Integer pid) ;

    //以树形形式根据文章id获取评论
    List<Comment> getListByTree(Integer articleId) ;
}
