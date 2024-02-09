package com.Minesweeper.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyApplicationException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorMessage;

    public MyApplicationException(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}