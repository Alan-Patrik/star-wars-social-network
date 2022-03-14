package com.starwars.resistence.modules.report.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class ReportDTO implements Serializable {
    private String title;
    private Instant createdAt;
    private String percentage;

    public ReportDTO(String title, Instant createdAt, String percentage) {
        this.title = title;
        this.createdAt = createdAt;
        this.percentage = percentage;
    }
}
