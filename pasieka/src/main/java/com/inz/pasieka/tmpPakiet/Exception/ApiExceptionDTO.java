package com.inz.pasieka.tmpPakiet.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionDTO {
    private final String message;
    private final HttpStatus httpStatus;

    public ApiExceptionDTO(String message, HttpStatus httpStatus  ) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
