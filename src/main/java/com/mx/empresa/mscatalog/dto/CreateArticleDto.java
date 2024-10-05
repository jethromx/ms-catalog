package com.mx.empresa.mscatalog.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticleDto{
    
    
    @Digits(integer = 8, fraction = 2, message = "Price must be a numeric value with up to 8 digits and 2 decimal places")
    private double price;

    @Size(max = 200, message = "Description can have at most 200 characters")
    private String description;

    @Size(max = 10, message = "Model can have at most 10 characters")
    private String model;

    @Size(max = 20, message = "Name can have at most 20 characters")
    private String name;
}