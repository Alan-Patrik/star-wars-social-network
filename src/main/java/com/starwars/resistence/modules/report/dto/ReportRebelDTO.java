package com.starwars.resistence.modules.report.dto;

import com.starwars.resistence.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ReportRebelDTO implements Serializable {
    private Long id;
    private String name;
    private GenderType gender;
    private int age;
    private String basename;
}
