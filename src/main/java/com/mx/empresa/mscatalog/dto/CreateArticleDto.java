package com.mx.empresa.mscatalog.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Price is required")
    private double price;

    @Size(max = 200, message = "Description can have at most 200 characters")
    @NotNull(message = "Description is required")
    private String description;

    @Size(max = 10, message = "Model can have at most 10 characters")
    @NotBlank(message = "Name is required")
    private String model;

    @Size(max = 20, message = "Name can have at most 20 characters")
    @NotBlank(message = "Name is required")
    private String name;
}