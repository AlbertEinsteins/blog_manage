package com.item.javaee.jpa;

import com.item.javaee.JavaEeApplication;
import com.item.javaee.entity.Comment;
import com.item.javaee.repository.CommentRepository;
import com.item.javaee.service.CommentService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CheckedOutputStream;

/**
 * @ClassName: Demo3
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-24 20:16
 * @Version: 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Demo3 {

    @Resource
    CommentRepository commentRepository ;

    @Resource
    CommentService commentService ;

    @org.junit.jupiter.api.Test
    @Transactional
    public void testSearch() {
        List<Comment> all = commentRepository.findAll() ;

        printTree(all.get(1), 1) ;

    }

    @Test
    public void insert() {
        Comment comment = new Comment() ;
        comment.setName("测试父亲") ;
        comment.setDesc("测试父亲内容") ;
        comment.setCommentTime(new Date()) ;
        comment.setArticleId(2) ;
        //一级分类
        comment.setParent(null) ;

        List<Comment> childs = new ArrayList<>() ;

        for(int i = 0 ; i < 5 ; i++) {
            Comment comment2 = new Comment() ;
            comment2.setName("测试儿子" + i) ;
            comment2.setDesc("测试儿子内容" + i) ;
            comment2.setCommentTime(new Date()) ;
            comment2.setArticleId(2) ;
            //设置父
            comment2.setParent(comment) ;
            childs.add(comment2) ;
        }

        comment.setChilds(childs) ;

        //从父亲这边保存
        commentRepository.save(comment) ;
    }

    private void printTree(Comment comment, int level) {
        StringBuilder pre = new StringBuilder();
        if(comment != null) {
            for(int i = 1 ; i < level ; i++) {
                pre.append("----");
            }
            System.out.println(pre + comment.getName()) ;

            comment.getChilds().forEach(item -> {
                printTree(item, level + 1) ;
            }) ;
        }
    }

    @Test
    @Transactional
    public void testSearchCommentsByArticleId() {
        List<Comment> listByTree = this.commentService.getListByTree(2) ;

        System.out.println(listByTree) ;

        listByTree.forEach(item -> {
            printTree(item,1) ;
        });

    }
}
