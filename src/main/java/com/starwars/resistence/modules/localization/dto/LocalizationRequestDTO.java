package com.starwars.resistence.modules.localization.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizationRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    private double latitude;

    @NotNull
    @NotBlank
    private double longitude;

    @NotNull
    @NotBlank
    private String basename;

    @Override
    public String toString() {
        return "{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", basename='" + basename + '\'' +
                '}';
    }
}
