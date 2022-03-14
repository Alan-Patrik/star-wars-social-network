package com.starwars.resistence.modules.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Instant createdAt;
    private Instant lastReportAt;
    private String message;
    private ReportRebelDTO rebelReported;
    private ReportRebelDTO rebelAccuser;
}
