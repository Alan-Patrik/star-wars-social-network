package com.starwars.resistence.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpExceptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String timestamp;
    private int code;
    private boolean success;
    private String exceptionName;
    private String path;
    private String message;
}
