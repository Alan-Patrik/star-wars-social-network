package com.starwars.resistence.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends CustomException {
    public CustomBadRequestException(String message) {
        super("Ops... Aconteceu um erro.", message, HttpStatus.BAD_REQUEST);
    }
}
