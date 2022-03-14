package com.starwars.resistence.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerException extends CustomException {
    public CustomInternalServerException(String message) {
        super("Ops... Aconteceu um erro no sistema.", message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
