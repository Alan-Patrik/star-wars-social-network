package com.starwars.resistence.modules.localization.builder;

import com.starwars.resistence.modules.localization.Localization;
import lombok.Builder;

@Builder
public class LocalizationBuilder {

    @Builder.Default
    private double latitude = -22.222222;

    @Builder.Default
    private double longitude = -44.4444444;

    @Builder.Default
    private String basename = "Galactic";

    public Localization buildLocalization() {
        return new Localization(latitude, longitude, basename);
    }
}