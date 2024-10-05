package com.mx.empresa.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.empresa.mscatalog.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {
}