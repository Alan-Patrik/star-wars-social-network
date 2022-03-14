package com.starwars.resistence.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@Builder
@AllArgsConstructor
public class HttpResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private String timestamp;
    private int code;
    private boolean success;
    private T data;

    public HttpResponseDTO(T data) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.timestamp = dateFormat.format(System.currentTimeMillis());
        this.code = HttpStatus.OK.value();
        this.success = true;
        this.data = data;
    }

    public HttpResponseDTO(HttpStatus status, T data) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        this.timestamp = dateFormat.format(System.currentTimeMillis());
        this.code = status.value();
        this.success = true;
        this.data = data;
    }

    @Override
    public String toString() {

        return "{" +
                "timestamp=" + timestamp +
                ", code=" + code +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
