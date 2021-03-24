package com.item.javaee.repository;

import com.item.javaee.entity.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: ArticleTagRepository
 * @Description TODO
 * @Author: jff
 * @Date: 2019-12-15 17:13
 * @Version: 1.0
 **/
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Integer> {
}
