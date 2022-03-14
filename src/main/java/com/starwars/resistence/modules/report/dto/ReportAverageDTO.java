package com.starwars.resistence.modules.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportAverageDTO {
    private String title;
    private String weapons;
    private String ammunition;
    private String food;
    private String water;
}
