package com.starwars.resistence.modules.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ReportTraitorsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private int traitors;
    private int total;
}
