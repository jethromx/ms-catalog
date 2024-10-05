package com.mx.empresa.mscatalog.service;


import com.mx.empresa.mscatalog.dto.CreateArticleDto;
import com.mx.empresa.mscatalog.dto.ServiceResponse;
import com.mx.empresa.mscatalog.dto.UpdateArticleDto;
import com.mx.empresa.mscatalog.entity.Article;

public interface ArticleService{
	
	public ServiceResponse<Article> findById(String id);
	
	public ServiceResponse<Article> updateArticle(String id, UpdateArticleDto updateArticleDto);
	public ServiceResponse<Article> createArticle(CreateArticleDto createArticleDto);

}
