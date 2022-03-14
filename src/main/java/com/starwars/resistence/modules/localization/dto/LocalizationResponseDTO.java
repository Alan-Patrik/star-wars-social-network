package com.starwars.resistence.modules.localization.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LocalizationResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private double latitude;
    private double longitude;
    private String basename;

    public LocalizationResponseDTO(Long id, double latitude, double longitude, String basename) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.basename = basename;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", basename=" + basename +
                '}';
    }
}
