package com.starwars.resistence.modules.localization.builder;

import com.starwars.resistence.modules.localization.dto.LocalizationRequestDTO;
import lombok.Builder;

@Builder
public class LocalizationRequestDTOBuilder {
    @Builder.Default
    private double latitude = -22.222222;

    @Builder.Default
    private double longitude = -44.4444444;

    @Builder.Default
    private String basename = "Galatico";

    public LocalizationRequestDTO buildLocalizationRequestDTO() {
        return new LocalizationRequestDTO(latitude, longitude, basename);
    }
}
