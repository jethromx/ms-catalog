package com.mx.empresa.mscatalog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mx.empresa.mscatalog.config.Constants;
import com.mx.empresa.mscatalog.dto.CreateArticleDto;
import com.mx.empresa.mscatalog.dto.ServiceResponse;
import com.mx.empresa.mscatalog.dto.UpdateArticleDto;
import com.mx.empresa.mscatalog.entity.Article;
import com.mx.empresa.mscatalog.exceptions.CustomException;
import com.mx.empresa.mscatalog.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/${api.version}/v0/articles")
@Tag(name = "Articles", description = "API for managing articles")
public class ArticleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    private static final String LOGLINE = "ArticleController - {} - {}";    

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {        
        this.articleService = articleService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new article", description = "Creates a new article with the provided details")
    public ResponseEntity<?> createArticle(@Valid  @RequestBody CreateArticleDto article,BindingResult bindingResult) {
        LOGGER.info(LOGLINE, Constants.METHOD_CREATE, Constants.IN);

        if (bindingResult.hasErrors()) {   
            LOGGER.error(LOGLINE, Constants.METHOD_CREATE, bindingResult.getAllErrors().get(0).getDefaultMessage());           
            throw  new CustomException(bindingResult.getAllErrors().get(0).getDefaultMessage(), "003");		
        }

        ServiceResponse<Article> response= articleService.createArticle(article);
        return handleResponse(response, Constants.METHOD_CREATE);
    }
    

    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID", description = "Retrieves an article by its ID")
    public ResponseEntity<?> getArticleById(@PathVariable String id) {
        LOGGER.info(LOGLINE, Constants.METHOD_GET, Constants.IN);

        

        ServiceResponse<Article> response = articleService.findById(id);
        return handleResponse(response, Constants.METHOD_GET);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an article", description = "Updates an existing article with the provided details")
    public ResponseEntity<?> updateArticle(
            @PathVariable String id,
            @Valid  @RequestBody UpdateArticleDto updateArticleDto,BindingResult bindingResult) {
        LOGGER.info(LOGLINE, Constants.METHOD_UPDATE, Constants.IN);

        if (bindingResult.hasErrors()) {  
            LOGGER.error(LOGLINE, Constants.METHOD_UPDATE, bindingResult.getAllErrors().get(0).getDefaultMessage());          
            throw  new CustomException(bindingResult.getAllErrors().get(0).getDefaultMessage(), "003");		
        }
        
        ServiceResponse<Article> response = articleService.updateArticle(id, updateArticleDto);
        LOGGER.info(LOGLINE, Constants.METHOD_UPDATE, Constants.OUT);
        return handleResponse(response, Constants.METHOD_UPDATE);
    }
        

    private ResponseEntity<?> handleResponse(ServiceResponse<?> response,String method) {
        if (response.getStatus().equals(com.mx.empresa.mscatalog.enums.Status.OK) && response.getResponseObject() != null) {         
            LOGGER.info(LOGLINE, method, Constants.OUT);
            return new ResponseEntity<>(response.getResponseObject(), response.getHttpCode());
           } else if (response.getStatus().equals(com.mx.empresa.mscatalog.enums.Status.KO) && response.getResponseObject() != null) {
               LOGGER.error(LOGLINE, method, Constants.ERROR);
               return new ResponseEntity<>(response.getResponseObject(), response.getHttpCode());
           } else {
               LOGGER.error(LOGLINE, method, Constants.ERROR);
               throw  new CustomException("An unexpected error occurred , please try again later", "100");               
        }
    }
}