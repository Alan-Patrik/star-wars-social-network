package com.starwars.resistence.modules.report.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ReportDTO<T> implements Serializable {
    private String title;
    private Instant createdAt;
    private String percentage;
    private T data;

    public ReportDTO(String title, Instant createdAt, String percentage, T data) {
        this.title = title;
        this.createdAt = createdAt;
        this.percentage = percentage;
        this.data = data;
    }
}
