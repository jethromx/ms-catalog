package com.mx.empresa.mscatalog.dto;


import com.fasterxml.jackson.annotation.JsonInclude;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateArticleDto {
	@Size(max = 200, message = "Description can have at most 200 characters")   
    private String description;

    @Size(max = 10, message = "Model can have at most 10 characters")
    private String model;

  
}