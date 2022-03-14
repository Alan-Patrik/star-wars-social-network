package com.starwars.resistence.exceptions;

import com.starwars.resistence.http.HttpExceptionDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionController {

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<HttpExceptionDTO> handleCustomBadRequestException(
            HttpServletRequest http, CustomBadRequestException exception
    ) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header("exception-name", exception.toString().split(":")[0])
                .header("error-code", BAD_REQUEST.getReasonPhrase())
                .header("error-message", exception.getMessage())
                .body(HttpExceptionDTO
                        .builder()
                        .timestamp(dateFormat.format(System.currentTimeMillis()))
                        .code(BAD_REQUEST.value())
                        .exceptionName(exception.toString().split(":")[0])
                        .path(http.getRequestURI())
                        .message(exception.getMessage())
                        .build());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(CustomTradeCanceledException.class)
    public ResponseEntity<HttpExceptionDTO> handleCustomTradeCanceledException(
            HttpServletRequest http, CustomTradeCanceledException exception
    ) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header("exception-name", exception.toString().split(":")[0])
                .header("error-code", BAD_REQUEST.getReasonPhrase())
                .header("error-message", exception.getMessage())
                .body(HttpExceptionDTO
                        .builder()
                        .timestamp(dateFormat.format(System.currentTimeMillis()))
                        .code(BAD_REQUEST.value())
                        .exceptionName(exception.toString().split(":")[0])
                        .path(http.getRequestURI())
                        .message(exception.getMessage())
                        .build());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public ResponseEntity<HttpExceptionDTO> handleCustomNotFoundException(
            HttpServletRequest http, CustomNotFoundException exception
    ) {
        return ResponseEntity
                .status(NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .header("exception-name", exception.toString().split(":")[0])
                .header("error-code", NOT_FOUND.getReasonPhrase())
                .header("error-message", exception.getMessage())
                .body(HttpExceptionDTO
                        .builder()
                        .timestamp(dateFormat.format(System.currentTimeMillis()))
                        .code(NOT_FOUND.value())
                        .exceptionName(exception.toString().split(":")[0])
                        .path(http.getRequestURI())
                        .message(exception.getMessage())
                        .build());
    }
}
