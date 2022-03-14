package com.starwars.resistence.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public class CustomException extends Exception {
    private static HttpStatus status = INTERNAL_SERVER_ERROR;
    private static String description = "Um erro interno";
    private final String defaultMessage = "Um erro interno do servidor foi lançado para";

    public CustomException(String message, String description) {
        super(String.format("%s %s", message, description));
        CustomException.description = description;
    }

    public CustomException(String message, String description, HttpStatus status) {
        super(String.format("%s %s", message, description));
        CustomException.description = description;
        CustomException.status = status;
    }
}
