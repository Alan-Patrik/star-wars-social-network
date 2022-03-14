package com.starwars.resistence.modules.localization.builder;

import com.starwars.resistence.modules.localization.dto.LocalizationResponseDTO;
import lombok.Builder;

@Builder
public class LocalizationResponseDTOBuilder {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private double latitude = -22.222222;

    @Builder.Default
    private double longitude = -44.4444444;

    @Builder.Default
    private String basename = "Galatico";

    public LocalizationResponseDTO buildLocalizationResponseDTO() {
        return new LocalizationResponseDTO(id, latitude, longitude, basename);
    }
}
