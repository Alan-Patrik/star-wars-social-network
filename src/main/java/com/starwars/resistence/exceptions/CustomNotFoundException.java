package com.starwars.resistence.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends CustomException {
    public CustomNotFoundException(String message) {
        super("Ops... Aconteceu um erro.", message, HttpStatus.NOT_FOUND);
    }
}
