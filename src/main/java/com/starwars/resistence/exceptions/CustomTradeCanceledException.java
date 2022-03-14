package com.starwars.resistence.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class CustomTradeCanceledException extends CustomException {
    public CustomTradeCanceledException(String message) {
        super("Ops.. Aconteceu um erro. ", message, BAD_REQUEST);
    }
}
