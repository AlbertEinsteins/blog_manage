package com.item.javaee.controller;

import com.item.javaee.entity.Article;
import com.item.javaee.entity.Comment;
import com.item.javaee.entity.Sort;
import com.item.javaee.entity.Tag;
import com.item.javaee.repository.es.EsArticleRepository;
import com.item.javaee.service.ArticleService;
import com.item.javaee.service.CommentService;
import com.item.javaee.service.SortService;
import com.item.javaee.service.TagService;
import com.item.javaee.utils.JsonBuilder;
import com.item.javaee.vo.EsPage;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName: MainController
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-05 20:30
 * @Version: 1.0
 **/
@Controller
public class MainController {

    @Resource
    SortService sortService;

    @Resource
    TagService tagService;

    @Resource
    ArticleService articleService;

    @Resource
    EsArticleRepository esArticleRepository;

    @Resource
    CommentService commentService ;

    //返回首页, 携带分类信息, 所有的标签信息
    @RequestMapping("/")
    public String indexPage(Model model) {
        //文章总数
        Long articleCount = this.articleService.countAll();
        //分类
        List<Sort> sortTree = this.sortService.getAllSortsByTree();
        Long sortCount = this.sortService.countAll();
        //标签
        List<Tag> tagList = tagService.getAllTags();

        model.addAttribute("articleCount", articleCount);
        model.addAttribute("sortTree", sortTree);
        model.addAttribute("sortCount", sortCount);
        model.addAttribute("tagList", tagList);
        return "index";
    }

    //根据sort_id查询Es所有的文章,分页
    @RequestMapping("/search_top/{sid}")
    public String searchTop(@PathVariable("sid") Integer sid,
                            Model model) {

        Sort sort = this.sortService.getById(sid);
        //顶部分类
        List<Sort> sortTree = this.sortService.getAllSortsByTree();

        //查询该分类总数
        EsPage<Article> esPage = this.esArticleRepository.getListBySortId(sid, 1, 1) ;
        //当前父级目录下的所有子目录
        List<Sort> childs = null;
        for (Sort sort1 : sortTree) {
            if (sort1.getSortId().equals(sort.getSortPid())) {
                childs = sort1.getChildren() ;
                break;
            }
        }


        //获取第一页的数据对分页进行初始化
        model.addAttribute("sonList", childs);
        model.addAttribute("sort", sort) ;
        //顶部所需信息
        model.addAttribute("sortTree", sortTree);
        model.addAttribute("total", esPage.getTotal()) ;

        return "search_top";
    }

    @RequestMapping(value = "/getPagableArticle", method = RequestMethod.GET)
    @ResponseBody
    public Object getPageableArticle(@RequestParam("sid") Integer sid,
                                     @RequestParam("pagenum") Integer pagenum,
                                     @RequestParam("pagesize") Integer pagesize) {
        Map<String, Object> result = new HashMap<>() ;

        //获取分页数据
        EsPage<Article> articleEsPage = esArticleRepository.getListBySortId(sid, pagenum, pagesize) ;

        result.put("data", articleEsPage.getElements()) ;
        result.put("total", articleEsPage.getTotal()) ;
        result.put("pagenum", pagenum) ;
        result.put("pagesize", pagesize) ;
        return JsonBuilder.buildJson(result, 200, "从搜索引擎获取数据成功!") ;
    }



    //跳转到详情页面
    @RequestMapping("/lookup/{aid}")
    public String getArticleContent(@PathVariable("aid") Integer aid,
                                    Model model) {
        //查阅量incre
        this.articleService.updateLookUpNumIncre(aid) ;

        //顶部分类
        List<Sort> sortTree = this.sortService.getAllSortsByTree();

        //获取文章内容
        Article byId = this.articleService.findById(aid);

        //获取文章所属分类
        Sort sort = this.sortService.getById(byId.getSortId()) ;

        //以树形组件获取该文章的所有评论
        List<Comment> comments = this.commentService.getListByTree(aid) ;


        model.addAttribute("sortTree", sortTree);
        model.addAttribute("sort", sort) ;
        model.addAttribute("article", byId) ;
        model.addAttribute("comments", comments) ;
        return "blog_lookup" ;
    }

    //保存评论
    @RequestMapping("/createComment/{aid}")
    @ResponseBody
    public Object saveComment(@PathVariable("aid") Integer aid,
                             @RequestParam(value = "parentId", required = false) Integer pid,
                             @RequestBody Comment comment) {
        if(comment == null) return JsonBuilder.buildJson(null, 202, "添加失败") ;

        comment.setArticleId(aid) ;

        this.commentService.saveComment(comment, pid) ;

        //评论数 increment
        this.articleService.updateCommentIncre(aid) ;

        return JsonBuilder.buildJson(null, 200, "成功!") ;
    }
}
