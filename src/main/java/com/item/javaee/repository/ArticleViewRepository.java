package com.item.javaee.repository;

import com.item.javaee.entity.view.ArticleView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleViewRepository extends JpaRepository<ArticleView, Integer> {
}
