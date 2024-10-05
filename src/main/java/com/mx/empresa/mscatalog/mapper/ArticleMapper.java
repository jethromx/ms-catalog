package com.mx.empresa.mscatalog.mapper;

import com.mx.empresa.mscatalog.dto.CreateArticleDto;
import com.mx.empresa.mscatalog.entity.Article;
import com.mx.empresa.mscatalog.util.GeneratorID;

public class ArticleMapper {

    public static Article toEntity(CreateArticleDto createArticleDto) {
        Article article = new Article();
        article.setId(GeneratorID.generateUniqueId(10)); // Asignar un ID único alfanumérico
        article.setName(createArticleDto.getName());
        article.setDescription(createArticleDto.getDescription());
        article.setPrice(createArticleDto.getPrice());
        article.setModel(createArticleDto.getModel());
        return article;
    }
}
