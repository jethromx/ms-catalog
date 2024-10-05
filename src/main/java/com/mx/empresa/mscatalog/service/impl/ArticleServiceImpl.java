package com.mx.empresa.mscatalog.service.impl;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mx.empresa.mscatalog.dto.CreateArticleDto;
import com.mx.empresa.mscatalog.dto.ServiceResponse;
import com.mx.empresa.mscatalog.dto.UpdateArticleDto;
import com.mx.empresa.mscatalog.entity.Article;
import com.mx.empresa.mscatalog.exceptions.CustomException;
import com.mx.empresa.mscatalog.mapper.ArticleMapper;
import com.mx.empresa.mscatalog.repository.ArticleRepository;
import com.mx.empresa.mscatalog.service.ArticleService;

import jakarta.transaction.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);
	private static final String LOGLINE = "ArticleServiceImpl - {} - {}";  


	private ArticleRepository articleRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public ServiceResponse<Article> findById(String id) {

		if(Strings.isBlank(id)) {
			LOGGER.error(LOGLINE, "findById","Article ID is required");  
			return new ServiceResponse<Article>(com.mx.empresa.mscatalog.enums.Status.KO, null, "Article ID is required", HttpStatus.BAD_REQUEST);
		}
		
		Optional<Article> articule;
		try {
			LOGGER.debug(LOGLINE, "findById","Buscando articulo "); 
			articule = articleRepository.findById(id);
			if (articule.isEmpty()) {
				LOGGER.error(LOGLINE, "findById","Article with ID " + id + " not found"); 			
				throw  new CustomException("Article with ID " + id + " not found", "001");
			}
	
			return new ServiceResponse<Article>(com.mx.empresa.mscatalog.enums.Status.OK, articule.get(), "Article with ID " + id + "", HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(LOGLINE, "findById","Error finding articule, please try again later");    
			throw  new CustomException("Error finding articule, please try again later", "001");			
		}
		

		
	}

	@Override
	public ServiceResponse<Article> createArticle(CreateArticleDto createArticleDto) {
		

		
        Article article =  ArticleMapper.toEntity(createArticleDto);

		try {
			LOGGER.debug(LOGLINE, "createArticle","Guardando articulo ");  
			article = articleRepository.save(article);
		} catch (Exception e) {
			LOGGER.error(LOGLINE, "createArticle","Error creating articule, please try again later", "000");  
			throw  new CustomException("Error creating articule, please try again later", "000");			
		}
		

		return new ServiceResponse<Article>(com.mx.empresa.mscatalog.enums.Status.OK, article, "Article created successfully", HttpStatus.CREATED);
    }


	@Override
	@Transactional
	public ServiceResponse<Article> updateArticle(String id, UpdateArticleDto updateArticleDto) {

		if(Strings.isBlank(id)) {
			LOGGER.error(LOGLINE, "updateArticle","Article ID is required");  
			throw  new CustomException("Article ID is required", "001");
		}

		LOGGER.info(LOGLINE, "updateArticle","Buscando articulo "); 
		ServiceResponse<Article> serviceResponse = this.findById(id);

		if (serviceResponse.getStatus().equals(com.mx.empresa.mscatalog.enums.Status.KO)) {
			LOGGER.error(LOGLINE, "updateArticle", serviceResponse.getMessage());
			return serviceResponse;
		}

		Article article = serviceResponse.getResponseObject();
				

		if (updateArticleDto.getDescription() != null) {
			article.setDescription(updateArticleDto.getDescription());
		}
		if (updateArticleDto.getModel() != null) {
			article.setModel(updateArticleDto.getModel());
		}

		Article articuleUpdated = articleRepository.save(article);

		return new ServiceResponse<Article>(com.mx.empresa.mscatalog.enums.Status.OK, articuleUpdated, "Article updated successfully", HttpStatus.OK);
	}



}
