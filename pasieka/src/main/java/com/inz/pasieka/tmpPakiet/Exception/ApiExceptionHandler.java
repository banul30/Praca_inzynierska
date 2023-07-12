package com.inz.pasieka.tmpPakiet.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ExistanceValidationException.class})
    public ResponseEntity<Object> handleToShortTimeToScreeningException(ExistanceValidationException ex){
        ApiExceptionDTO apiEx = new ApiExceptionDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );


        return new ResponseEntity<>(apiEx, HttpStatus.BAD_REQUEST);
    }


}
