package com.mx.empresa.mscatalog.dto;

import org.springframework.http.HttpStatus;

import com.mx.empresa.mscatalog.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse<T> {

    private final Status status;

    private final T responseObject;

    private final String message;

    private HttpStatus httpCode;

   
   

}
